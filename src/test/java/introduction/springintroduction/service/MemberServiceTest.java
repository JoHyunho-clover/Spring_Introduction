package introduction.springintroduction.service;

import introduction.springintroduction.domain.Member;
import introduction.springintroduction.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트를 실행하기 이전에 초기화 시켜주는 것이랑 마찬가지다
    // 이것을 하게 된 이유는 MemberService에 있는 MemoryMemberRepository를 Test에서 다르게 가지고 있을 필요가 없기 때문이다
    // 이렇게 하는 것이 Denpendency Injection이다.
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}