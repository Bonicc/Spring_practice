package hello.hello.service;

import hello.hello.domain.Member;
import hello.hello.repository.MemberRepository;
import hello.hello.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // @ComponentScan 방식은 나중에 Repository가 바뀌는 다른 클래스를 사용할 때, 바꿀게 많음
    // 근데 이 직접 주입방식은 new 부분만 바꿔주면됨(인터페이스 생성자 부분만 바꿔주면됨
}
