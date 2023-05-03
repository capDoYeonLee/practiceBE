package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); // save하면 회원이 레포지토리에 저장 됨.

    // #TODO Optional은 무슨 기능을 하는지??
    Optional<Member> findById(Long id); // Id로 회원 탐색

    Optional<Member> findByName(String name);  // Name으로 회원 탐색

    List<Member> findAll(); // 지금까지 레포지토리에 저장된 모든 회원 반환
}