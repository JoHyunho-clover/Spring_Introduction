package introduction.springintroduction.repository;

import introduction.springintroduction.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영구저장한다는 메소드
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회하는 메서드 단, 열이 PK인 경우 이렇게 조회 가능
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { //조회하는 메서드 단, 열이 PK가 아닌 경우 이렇게 조회 가능
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();//여기서는 where로써 조건이 있기에 findAll이 안되고 name에 대한 것만 return된다.
        return result.stream().findAny(); //하나만 찾으니까 findAny로 진행
    }

    @Override
    public List<Member> findAll() { //조회하는 메서드 단, 열이 PK가 아닌 경우 이렇게 조회 가능
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); //테이블이름이 아닌 Entity대상으로 쿼리를 날린다. *이 아니라 객체 자체를 조회하게 진행한다.
    }
}
