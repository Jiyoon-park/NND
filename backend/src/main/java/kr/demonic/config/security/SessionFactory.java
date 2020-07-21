package kr.demonic.config.security;

import kr.demonic.jpa.entity.Member;
import kr.demonic.jpa.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 세션을 구성하는 클래스
 */
@Component
public class SessionFactory {
    @Autowired
    MemberRepository memberRepository;

    // 세션생성 (일반로그인)
    public void setSession(HttpServletRequest request, String email){
        Member member = memberRepository.findByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("name", member.getName());
    }
}
