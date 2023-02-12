package hello.hello.service;

import hello.hello.domain.Member;
import hello.hello.repository.MemberRepository;
import hello.hello.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


// ctrl shfit T
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * @brief 회원 가입 함수
     * @param member
     * @return
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원은 안됨
        // ctrl alt shift T : refactoring
        validateDuplicateMember(member);
        memberRepository.save((member));
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findbyName(member.getName())
                .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 전체 회원 조회 함수
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long Id){
        return memberRepository.findbyId(Id);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
