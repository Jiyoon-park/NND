package kr.demonic.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 리소스들은 인증을 풀어줘줌.
        web.ignoring().antMatchers("/css/**", "/script/**", "/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").authenticated()
                // ADMIN 권한이 있는것만 가능.
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()        // form 태그 기반 로그인 지원 설정.
                    .loginPage("/login")
                    .loginProcessingUrl("/loginAction")
                    // 성공했을 경우 아래의 설정으로 하면 특정조건하에 login_success로 콜백이 안되는 경우가 있음
                    // 조건: 다른 페이지로 이동하다가 auth 에 걸려서 login 으로 이동하게 된 경우 등.
                    //.defaultSuccessUrl("/login_success")
                    .successForwardUrl("/login_success")
                    .failureUrl("/login_fail")
                    // 로그인 ID 비교할 파라미터.
                    // 만약 아랫것을 바꾼다면 CustomUserDetailService.loadUserByUsername 의 파라미터도 변경되어야 함.
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                .key("loginTest")           // 쿠키값으로 암호화된 값을 전달하고, 로그인 상태를 기억합니다.
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(2419200)          // 쿠키유지 시간(설정되어있지 않으면 기본 2주)
                .tokenRepository(getJDBCRepository())       // DB에 토큰 저장. DB 테이블 생성은 아래주석 참조.
            ;
    }


    /*
    * 토큰을 DB에 저장하기 위해 사용되는 메서드.(디비에 토큰을 저장하지 않을 거라면 사용하지 않아도 됨)
    * ID / PASSWORD 구조로 되어있는 로그인에서 활용
    * DB에 다음 스키마 추가
                   create table persistent_logins (username varchar(64) not null,
                                series varchar(64) primary key,
                                token varchar(64) not null,
                                last_used timestamp not null);
    */
    private PersistentTokenRepository getJDBCRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    // 패스워드 암호화 구현체
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 인증매니저를 생성하는 빌더
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
}
