package com.pt.brewit.controller.main;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
@Slf4j  // log 사용위해 추가 (lombok 어노테이션)
@RequiredArgsConstructor // final, @NonNull 어노테이션붙은 변수를 매개변수로 갖는 생성자 자동생성
public class MemberController {

    //@Autowired  // #1. 자동주입
    // #2. Setter 이용한 자동 주입
    //@Setter(onMethod_ = @Autowired)
    //private MemberMapper memberMapper;
    // #3. 생성자를 이용한 주입 (*)
    private final MemberMapper memberMapper;
    private final MybatisProperties mybatisProperties;
    private final MemberService memberService;
    /* Lombok의 어노테이션으로 대체
    public MemberController(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }*/

    // 회원 목록 페이지 요청
    @GetMapping  // http://localhost:8080/members
    public String members(Model model) {
        log.info("GET /members 회원 목록 요청!");
        // DB에서 회원전체목록가져와 화면에 전달
        List<MemberDTO> list = memberMapper.selectAll();
        model.addAttribute("list", list);

        return "members/list";
    }

    // 회원 가입 폼 요청
    @GetMapping("/new")
    public String newForm(@ModelAttribute("member") MemberDTO member) {
        return "/main/members/newForm";
    }


    // 회원가입 처리 요청
    @PostMapping("/new")
    public String newPro(MemberDTO member, String au) {
        log.info("newPro - member : {}", member);
        log.info("newPro - au : {}", au);
        // 회원 가입 처리
        int result = memberService.register(member, au);
        log.info("newPro - result : {}", result);

        return "redirect:/"; // 페이지 이동 = "/" 경로 코드로 요청 -> @..Mapping("/") 메소드호출
    }

    // 마이페이지 요청
    @GetMapping("/{email}")
    public String mypage(@PathVariable("email") String username, Model model){
        log.info("mypage username: {}", username);
        MemberDTO member = memberService.getMember(username);
        model.addAttribute("member", member);
        return "/main/members/mypage";
    }

    // 회원 정보 수정 폼 요청
    @GetMapping("/modify/{email}")
    public String modifyPage(@PathVariable("email") String username, Model model){
        log.info("modifyPage username: {}", username);
        MemberDTO member = memberService.getMember(username);
        model.addAttribute("member", member);
        return "/main/members/modify";
    }

    @PostMapping("/modify/{email}")
    public String modify(@PathVariable("email") String username, MemberDTO member){
        log.info("modifyPage username: {}", username);
        log.info("modifyPage member: {}", member);
        member.setEmail(member.getEmail());
        member.setCellphone(member.getCellphone());
        member.setTelephone(member.getTelephone());
        member.setAddress1(member.getAddress1());
        member.setAddress2(member.getAddress2());
        memberService.updateMember(member);

        return "redirect:/main/{email}";
    }

     // 회원 삭제 요청
    @PostMapping("/delete/{email}")
    public String delete(@PathVariable("email") String username, HttpSession session) {
        log.info("delete username: {}", username);
        memberService.deleteMember(username);
        // 로그아웃 처리 -> 방법#1. /logout GET 이면 redirect:/logout (스프링시큐리티 로그아웃은 default POST)
        // -> 방법#2. SecurityContextHolder 이용
        SecurityContextHolder.clearContext(); // SecurityContext(인증관련저장소) 초기화 -> 로그아웃
        return "redirect:/";
    }
    // email 중복확인 팝업 요청
    @GetMapping("/emailAvail")
    public String emailAvail(String email, Model model) {
        // 전달받은 email값이 DB에 존재하는지 여부 판단해서 view에 결과 전달
        boolean result = true; // 초기값은 사용가능으로 넣어놓고 밑에서 판단
        MemberDTO memberDTO = memberMapper.selectOne(email);
        if (memberDTO != null) {
            result = false; // 이미 사용중 -> 사용 불가
        }
        // 이미 사용중이면 false, 사용가능한 email면 true
        model.addAttribute("result", result);

        return "main/members/emailAvail";
    }

    // email 중복 ajax 요청 --> 리턴은 결과 데이터 (html X)
    @PostMapping("/emailAvailAjax")
    @ResponseBody  // -> HTML 화면결과가 아닌, 데이터를 응답하겠다~~
    public String emailAvailAjax(String email) {
        log.info("Ajax email: {}", email);
        String result = "사용가능합니다";
        MemberDTO memberDTO = memberMapper.selectOne(email);
        if (memberDTO != null) {
            result = "이미사용중입니다..";
        }
        return result; // 문자열자체를 응답
    }
}
