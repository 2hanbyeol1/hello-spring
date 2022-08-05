package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // 인터페이스 다중상속 가능
    // JpaRepository를 extends한 인터페이스를 만들면 Spring Bean에 자동 등록됨

    @Override
    Optional<Member> findByName(String name); // 공통 클래스로 제공하는 것이 불가능 (비즈니스 로직 따라 다름)
    // 메서드 네임이 findBy~~일 때 JPQL select m from Member m where m.~~ = ?
}
