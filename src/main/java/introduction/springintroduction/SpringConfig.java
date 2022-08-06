package introduction.springintroduction;

import introduction.springintroduction.aop.TimeTraceAop;
import introduction.springintroduction.repository.MemberRepository;
import introduction.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //스프링 Data Jpa사용시
    private final MemberRepository memberRepository; // 여기에 이렇게 하면, 스프링 데이터 JPA가 만들어 놓은 구현체가 여기의 생성자의 인자로 들어가 주입이 된다.

    @Autowired //스프링 Data Jpa만 사용시 생성자가 하나라서 이럴때에는 이 어노테이션 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        //이것을 실행할 때 스프링 컨테이너에서 memberRepository를 찾는다. 하지만, 등록된 memberRepository가 없다.
        //하지만, 하나가 있다. 스프링 데이터 JPA가 JpaRepository<?,?>를 상속한 것을 보고 자동으로 등록해준다. 이때 등록해준 것이 다중 상속을 받고 있으니
        //다중 상속 받고 있는 인터페이스에 대해 빈으로 등록이 된 것이다.
        // SpringDataJpaMemberRepository 인터페이스에 대한 구현체를 스프링 Data Jpa가 어떤 기술을 가지고 만들고, 스프링 빈에 등록한다.
        //그래서 injection을 받을 수 있다.
        this.memberRepository = memberRepository;
    }

    /*
    private final EntityManager em; //JPA시 사용

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/
    /*
    private final DataSource dataSource; //순수 JDBC와 JDBCTemplate사용시

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    //스프링 Data Jpa사용시 MemberService에 의존 관계를 setting해주어야한다.
    @Bean public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*@Bean //이것은 스프링 Data Jpa 사용 이외에 다 사용
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean public MemberRepository memberRepository() {

        //return new MemoryMemberRepository(); //데이터베이스 이용안하고 JVM의 메모리 이용
        //return new JdbcMemberRepository(dataSource); //순수 JDBC이용
        //return new JdbcTemplateMemberRepository(dataSource); //JDBCTemplate이용
        //return new JpaMemberRepository(em); //Jpa 사용
    }*/

    /*
    @Bean //AOP 빈 등록
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/
}
