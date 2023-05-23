package project.board.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.board.Service.MemberService;
import project.board.domain.Member;
import project.board.dto.MemberDto;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberJoinForm")
    public String addForm() {
        return "member/memberJoinForm";
    }


    @PostMapping("/memberJoinForm")
    public String createMember(@ModelAttribute MemberDto member) {
        memberService.joinUser(member);
        return "redirect:/";
    }

    @GetMapping("/memberLoginForm")
    public String login() {
        return "member/memberLoginForm";
    }

    @GetMapping("/memberLoginResult")
    public String loginResult() {
        return "member/memberLoginResult";
    }

    @GetMapping("/memberList")
    public String findAllMember(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "member/memberList";
    }
}