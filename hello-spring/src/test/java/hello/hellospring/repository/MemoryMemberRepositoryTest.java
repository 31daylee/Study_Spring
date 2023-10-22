package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest { // 개발을 다 끝낸 후에 테스트케이스를 작성함- 이와 반대되게 테스트케이스 작성을 끝낸 후에 개발을 할 수도 있음=> TDD(테스트 주도 개발)

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 캐쉬를 지워주는 메서드- 테스트가 실행되고 끝날때마다 리포지토리의 캐쉬를 지움
    public void afterEach() {
        repository.clearStore();

    }


    @Test
    public void sava() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result); // member는 result 와 동일한가? 를 테스트케이스로 테스트 해보기
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}
