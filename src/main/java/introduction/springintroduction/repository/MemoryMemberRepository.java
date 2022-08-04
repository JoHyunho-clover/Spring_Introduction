package introduction.springintroduction.repository;

import introduction.springintroduction.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>(); //실무에서는 이거은 동시성 문제가 있을 수 있다. 이때에는 ConcurrentHashMap을 사용해야 한다.
    private static long sequence = 0L; //위의 store map의 키값을 생성해주는 역할

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //이름은 사용자가 입력하는 것이라서.. 여기서 안넣음
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store.get(id)이 null이면 감싸주어서 반환해라 이렇게 하면 클라이언트에서 할 수 있는 것이 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny(); //하나라도 찾는 것으로 Optional로 반환한다. -> map의 value들을 loop 다 돌면서 찾으면 그것에 해당하는 member반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
