package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * JPA
 */
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA를 쓰려면 EntityManager를 주입받아야 한다.

    // build.gradle의 data-jpa library를 받으면, 스프링부트가 자동으로 EntityManager를 만들어준다. -> injection
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // setId까지 모든걸 다해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // find(entityClass, PK) -> PK로 조회하는 경우
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) // PK로 조회하지 않는 경우, JPQL(객체 지향 쿼리 언어) 작성
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
