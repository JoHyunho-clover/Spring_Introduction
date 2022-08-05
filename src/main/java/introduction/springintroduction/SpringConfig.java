package introduction.springintroduction;

import introduction.springintroduction.repository.JpaMemberRepository;
import introduction.springintroduction.repository.MemberRepository;
import introduction.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em; //JPA시 사용

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    /*
    private final DataSource dataSource; //순수 JDBC와 JDBCTemplate사용시

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {

        //return new MemoryMemberRepository(); //데이터베이스 이용안하고 JVM의 메모리 이용
        //return new JdbcMemberRepository(dataSource); //순수 JDBC이용
        //return new JdbcTemplateMemberRepository(dataSource); //JDBCTemplate이용
        return new JpaMemberRepository(em);
    }
}
