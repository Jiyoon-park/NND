package kr.demonic.config.security;

import kr.demonic.jpa.entity.Member;
import kr.demonic.jpa.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * [Spring Security] UserDetailsService 인터페이스 구현체
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        UserDetails user= new User(email, member.getPassword(), authorities(member));

        return user;
    }

    // Spring Security Role 설정
    private static Collection<? extends GrantedAuthority> authorities(Member member){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(member.getEmail().equals("test@test.com")){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
