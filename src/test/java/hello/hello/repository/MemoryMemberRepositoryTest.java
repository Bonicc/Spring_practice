package hello.hello.repository;

import hello.hello.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        Member member2 = new Member();
        member.setName("spring");
        member2.setName("spring"); // member2 로 같은 이름인 spring 으로 설정한 경우에는,
                                    // println 은 true 인데, assertions 에서는 false(에러 발생)

        repository.save(member);

        Member result = repository.findbyId(member.getId()).get();
        System.out.println("result = " + (result == member));

//        Assertions.assertEquals(result, member);
        Assertions.assertThat(member).isEqualTo(result);


        // repository.findbyId(1);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findbyName("spring1").get();
        Member result2 = repository.findbyName("spring2").get();

        Assertions.assertThat(member1).isEqualTo(result1);
        Assertions.assertThat(member2).isEqualTo(result2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> resultList = repository.findAll();

        Assertions.assertThat(resultList.size()).isEqualTo(2);
    }

}
