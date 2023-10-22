package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { // find~ 로 저장소에서 찾을 수 있게 정의해둠
    Member save(Member member);
    Optional<Member> findById(Long Id); // optional 은 타입을 반환하는데 null 값 역시도 처리할 수 있게함
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
