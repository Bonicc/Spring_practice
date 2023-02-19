package hello.hello.controller;

import hello.hello.domain.Member;
import hello.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// componentScan과 자동 의존관계 설정
// Autowired, Service(Component)
// 스프링 올라올 때(annotation 생성 될 때), 생성된다.
// 이때, new 방식을 그냥 자동적으로 해준다는 의미인듯?
// 원래는 막 다 연결해줘야 하는데, 그런거 무시하고 아마 autoWired 로 한번에 하는 거 같음.......

// #Configuration, @Bean
// 수동 시에는 생성자를 직접 만들어주고, 생성자에 대한 것을 넣어주는 걸 새롭게 정의
@Controller
public class MemberController {

    private final MemberService memberService; // 등록하기 위해서 사용

    // constructor, alt+Insert
    //@Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 자동 의존관계에서 설정할 수 있는 방법 3가지
    // injector를 3개를 쓸 수 있음.
    // 1. 생성자 injector
    // 생성자를 통해서 들어오는 방법
    // 2. 필드 injector
    // 생성자를 뺴고, 멤버 변수 선언 문에다가 @Autoewired 넣는것
    // 보통은 잘 안씀.....
    // 3. setter injector
    // set 함수로 controller의 멤버 변수를 대입하는 방법
    // 보통 set은 퍼블릭으로 세팅되는 부분이 있어성 나중에 변경될 수도 있음??
    // 중간에 바뀔 필요없는것들에 대해서는 이렇게 하면 애매함..

    // 보통 컨트롤러는 서비스 중에는 바뀔일이 없으므로(안에 내용물도?)
    // 차라리 1번이 가장 선호됨.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
