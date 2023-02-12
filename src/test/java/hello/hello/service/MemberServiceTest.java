package hello.hello.service;

import hello.hello.domain.Member;
import hello.hello.repository.MemberRepository;
import hello.hello.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입_테스트() {
        // given
        Member member = new Member();
        member.setName("user1");

        //when
        Long saveId = memberService.join(member);

        //then
        Optional<Member> user1 = memberService.findOne(saveId);
        Assertions.assertEquals(user1.get(), member);


    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("user1");
        Member member2 = new Member();
        member2.setName("user1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join((member2)));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다");

//        assertThrows(NullPointerException.class, () -> memberService.join((member2)));

//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail("테스트 예외가 발생해야 합니다.");
//        }catch(IllegalStateException e ) {
//
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}