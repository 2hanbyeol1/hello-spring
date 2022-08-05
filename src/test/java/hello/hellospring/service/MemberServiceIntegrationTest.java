package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 통합 테스트
@Transactional // 테스트 완료 후, DB 상태 Rollback
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; // Test기 때문에 필드 주입해도 괜찮음
    @Autowired MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * 중복_회원_예외
     */
    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("한별중복");

        Member member2 = new Member();
        member2.setName("한별중복");

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}