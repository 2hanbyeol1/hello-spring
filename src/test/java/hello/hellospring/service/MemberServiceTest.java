package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 회원가입
     */
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("한별중복");

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

        /*
        assertThrows가 아닌 try-catch를 이용한 예외 검증
        try {
            memberService.join(member2);
            fail("중복 예외가 발생하지 않았습니다. 테스트 실패");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}