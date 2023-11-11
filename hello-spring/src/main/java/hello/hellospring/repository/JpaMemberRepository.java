package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa 는 em 으로 모두 동작하게된다. 스프링 부트가 자동으로 em을 생성하게 된다.-> DB와 통신하는 역할을 내부적으로 한다.
    // JPA 를 사용하려면 em 을 주입받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);  // jpa 가 insert 쿼리 다 만들어서 db에 집어넣음
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); // optional 타입으로 리턴함. 값이 있을 수도 있고 없을 수도 있기에 ofNullable 사용
    }

    // 아이디는 위와 같은 find 통해서 찾기 가능
    // 이름은 JPQL 라는 객체지향 쿼리 사용
    // Optional 이기때문에 result.stream.findAny() 가 추가됨 (findAll과 비교)
    // findByName 등 pk기반이 아닌 여러 개의 리스트를 가지고 돌릴 때는 jpql 로 작성
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    // jpql 은 객체를 대상으로 쿼리를 날린다. 그러면 이것이 sql로 번역된다.
    // select m 에서 m은 Member 객체 자체를 select 한다
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
