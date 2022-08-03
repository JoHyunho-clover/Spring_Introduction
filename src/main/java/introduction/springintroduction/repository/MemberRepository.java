package introduction.springintroduction.repository;

import introduction.springintroduction.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id); //id로 회원을 찾기
    Optional<Member> findByName(String name); //만약 id나 name으로 회원을 못찾았을 경우 null을 리턴하는데 null을 Optional로 감싸주기 위함
    List<Member> findAll(); //모든 회원 리턴
}
