package kr.demonic.service;

import kr.demonic.config.security.CustomAuthenticationProvider;
import kr.demonic.config.security.CustomUserDetailService;
import kr.demonic.config.security.SessionFactory;
import kr.demonic.jpa.entity.Member;
import kr.demonic.jpa.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Log
@Controller
public class LoginController {

    @Autowired
    private MemberRepository memberRepository;

    // 세션생성 및 관리.
    @Autowired
    private SessionFactory sessionFactory;

    // Spring Security UserDetailsService 구현체
    @Autowired
    private CustomUserDetailService customUserDetailService;

    // 회원가입 중 동일한 정보가 있는 경우 로그인 시키기 위해 사용되는 인터페이스
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;


    @RequestMapping(path = {"/", "/main"})
    public String main(Model model, HttpSession session){
        String email = session.getAttribute("email") == null ? (String) session.getAttribute("email") : "";
        model.addAttribute("email", email);
        return "main";
    }

    /**
     * 인증없이 접근가능한 페이지
     * @return
     */
    @GetMapping("/no_login")
    public String noLogin(){
        return "no_login";
    }

    @RequestMapping(path = {"/login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/login_fail")
    public String loginFail(){
        return "login_fail";
    }

    // 로그인 성공 및 세션 생성
    @RequestMapping("/login_success")
    public String login_success(HttpServletRequest request, @AuthenticationPrincipal User user){
        // 세션 생성
        sessionFactory.setSession(request, user.getUsername());

        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if(redirectUrl != null){
                return redirectUrl;
            }

            // 로그인을 호출했던 URL 을 로드.
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(defaultSavedRequest != null){
                redirectUrl = defaultSavedRequest.getRequestURI();
                if(redirectUrl != null){
                    return "redirect:"+redirectUrl;
                }
            }
        }
        return "redirect:/main";
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response
                            , @ModelAttribute Member member){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ( auth != null ){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/main";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> sessionParams = session.getAttributeNames();
        while(sessionParams.hasMoreElements()){
            String key = sessionParams.nextElement();
            System.out.println("Session Info: " + key + " => " + session.getAttribute(key));
        }

        return "admin";
    }

    @RequestMapping("/admin/test")
    public String admin_test(@AuthenticationPrincipal User userCustom){
        return "admin_test";
    }


    // 회원가입
    @RequestMapping(path="/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute Member member, HttpServletRequest request){
        PasswordEncoder pe = new BCryptPasswordEncoder();
        member.setPassword(pe.encode(member.getPassword()));

        Member searchMember = memberRepository.findByEmail(member.getEmail());
        if (searchMember == null){
            memberRepository.save(member);
        }

        // 로그인 처리 진행
        // 세션처리
        sessionFactory.setSession(request, member.getEmail());
        // Auth 처리
        UserDetails userDetails = customUserDetailService.loadUserByUsername(searchMember.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword(), userDetails.getAuthorities());
        customAuthenticationProvider.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        return "redirect:/";
    }


}
