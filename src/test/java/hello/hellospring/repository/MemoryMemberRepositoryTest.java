package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("한별");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result); // expected, actual
        assertThat(result).isEqualTo(member); // actual, expected
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("한별1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("한별2");
        repository.save(member2);

        Member result = repository.findByName("한별1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("한별1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("한별2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
