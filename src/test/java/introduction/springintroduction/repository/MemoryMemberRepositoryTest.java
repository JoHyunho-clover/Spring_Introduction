package introduction.springintroduction.repository;

import introduction.springintroduction.domain.Member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository= new MemoryMemberRepository();

    //각 테스트가 끝나고 콜백함수처럼 이것이 실행된다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //Optinal이 반환 값이라 get으로 다시 한다.

        assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(result,member); //import org.junit.jupiter.api.Assertions;
        //System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 누르면 변수 한번에 바꿀 수 O
        member2.setName("spring2");
        repository.save(member2);

        Member result= repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
