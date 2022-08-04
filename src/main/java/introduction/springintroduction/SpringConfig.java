package introduction.springintroduction;

import introduction.springintroduction.repository.MemberRepository;
import introduction.springintroduction.repository.MemoryMemberRepository;
import introduction.springintroduction.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
