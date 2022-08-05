package introduction.springintroduction;

import introduction.springintroduction.repository.JdbcMemberRepository;
import introduction.springintroduction.repository.JdbcTemplateMemberRepository;
import introduction.springintroduction.repository.MemberRepository;
import introduction.springintroduction.repository.MemoryMemberRepository;
import introduction.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {

        //return new MemoryMemberRepository(); //데이터베이스 이용안하고 JVM의 메모리 이용
        //return new JdbcMemberRepository(dataSource); //순수 JDBC이용
        return new JdbcTemplateMemberRepository(dataSource); //JDBCTemplate이용
    }
}
