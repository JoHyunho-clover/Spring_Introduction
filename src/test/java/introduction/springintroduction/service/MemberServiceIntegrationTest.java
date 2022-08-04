package introduction.springintroduction.service;

import introduction.springintroduction.domain.Member;
import introduction.springintroduction.repository.MemberRepository;
import introduction.springintroduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //spring테스트할 때 이거 사용 즉, 스프링 컨테이너와 테스트를 함께 실행
@Transactional  //이걸 testcase에 넣으면 DB에 데이터넣고 검증한 뒤 되든 안되든 테스트 완료 후에 항상 Rollback해서 DB에 반영안되게 한다.
class MemberServiceIntegrationTest {

    //테스트할때에는 필드 주입써도됨.
    @Autowired MemberService memberService;

    @Autowired MemberRepository memberRepository;


    //test 코드는 빌드할 때 안들어가기에, 한글로 직관적으로 알아보게 적어도 된다.
    @Test
    void 회원가입() {
        //given  어떠한 상황이 주어지고
        Member member = new Member();
        member.setName("hello");

        //when  어떠한 걸로 실행하면
        Long saveId=memberService.join(member);

        //then 어떠한 결과가 나와야해.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //test는 예외도 해보는게 중요!! 예외처리한것도 되는지 봐야해
    @Test
    public void 중복_회원_예외(){
        //given  어떠한 상황이 주어지고
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        //when  어떠한 걸로 실행하면
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2)); //람다식에 해당하는 로직을 돌리는데 앞의 Excetion이 터져야된다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            //then 어떠한 결과가 나와야해.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then 어떠한 결과가 나와야해.

    }
}