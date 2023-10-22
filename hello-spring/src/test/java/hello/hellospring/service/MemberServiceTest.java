package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long savaId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(savaId).get();
        Assertions.assertThat(member.getName()).
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}