package introduction.springintroduction.controller;

import introduction.springintroduction.domain.Member;
import introduction.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//스프링 컨테이너에 스프링 빈으로써 등록해주는 어노테이션
public class MemberController {

    private final MemberService memberService; //스프링이 관리하게 되면 다 스프링 컨테이너에 등록하고 스프링 컨테이너로부터 받아서 사용하는 것으로 바꾸어야한다.
    //여기에서 new MemberService();를 통해 작성한다면, 이 컨트롤러말고 memberService를 다른 컨트롤러들이 사용할 수 있게 된다.
    // 따라서 스프링 컨테이너에 등록하면, 딱하나만 등록되기에 이것으로 사용하면 된다.

    //스프링이 동작할때 스프링 컨테이너가 생성되고 이때 MemberController객체를 생성해서 가지고 있는다고 하였다. 이때 생성자를 호출한다.
    //생성자 호출시 그 생성자에 @Autowired 어노테이션이 설정되어 있는 경우 스프링 컨테이너가 스프링 컨테이너내에 있는 MemberService를 가져다가 연결시켜준다.
    //그런데 MemberService라는 클래스가 스프링 컨테이너 내의 스프링 빈으로 등록되어 있어야된다.
    //즉, Service와 Repository들도 스프링 빈으로써 등록해두어야한다. 그래서 @Service @Repository를 적어야한다.
    // 각각의 Service와 Repository들도 스프링 빈들끼리 생성자에 생성해서 가지고 있는 것을 하였다면 @Autowired를 통해 연결시켜주어야한다

    //스프링이 동작할때 스프링 컨테이너가 생성되고 이때 MemberController객체를 생성해서 가지고 있는다고 하였다. 이때 생성자를 호출한다.
    //생성자가 Autowired설정이 되어있다면, 스프링이 스프링 빈으로 등록된 memberservice객체를 이 생성자에 넣어줍니다. 이것이 DI(Denpendency Injection)인 의존성 주입이다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

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
        List<Member> mebers=memberService.findMembers();
        model.addAttribute("members",mebers);
        return "members/memberList";
    }
}
