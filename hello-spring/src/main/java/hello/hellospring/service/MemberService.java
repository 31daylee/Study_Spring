package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

// jpa 기술을 사용할 때 service에서 transactional이 필요함
@Transactional
public class MemberService { // 비즈니스 처리에 가까움

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*회원가입*/
    public Long join(Member member){
       // 같은 이름이 있는 중복 회원 X

       long start = System.currentTimeMillis();

       try{
           validateDuplicateMember(member); // 중복 회원 검증
           memberRepository.save(member);
           return member.getId();

       }finally {
           long finish =System.currentTimeMillis();
           long timeMs = finish-start;
           System.out.println("join = " + timeMs+ "ms");
       }

    

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");

                 });
    }
    
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
