package hello.hello.repository;

import hello.hello.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findbyId(Long id);
    Optional<Member> findbyName(String name);
    List<Member> findAll();
}
