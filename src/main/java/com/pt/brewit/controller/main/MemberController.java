package com.pt.brewit.controller.main;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
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
    public String newMember() {
        return "members/newForm";
    }

    // 회원가입 처리 요청
    @PostMapping("/new")
    public String newMemberPro(MemberDTO memberDTO) {
        log.info("memberDTO: {}", memberDTO);
        // DB 저장
        memberMapper.insertMember(memberDTO);

        return "redirect:/"; // 페이지 이동 = "/" 경로 코드로 요청 -> @..Mapping("/") 메소드호출
    }

    // 마이페이지 요청
    @GetMapping("/{id}") // ...8080/members/사용자id
    public String editMember(@PathVariable("id") String id) {
        log.info("mypage id: {}", id);
        return "members/mypage";
    }

    // 회원 정보 수정폼 요청
    @GetMapping("/{id}/modify")
    public String modifyMember(@PathVariable("id") String id, Model model) {
        log.info("modifyForm id: {}", id);
        // 해당 유저의 정보를 DB에서 가져와 화면에 전달
        MemberDTO memberDTO = memberMapper.selectOne(id);
        model.addAttribute("member", memberDTO);
        return "members/modify";
    }



    // 회원 탈퇴 폼 요청
    @GetMapping("/{id}/delete")
    public String deleteMember(@PathVariable("id") String id) {
        log.info("delete form - id: {}", id);
        return "members/delete";
    }

    // 회원 탈퇴 처리 요청
    @PostMapping("/{id}/delete")
    public String deleteMemberPro(@PathVariable("email") String email, String pw, Model model, HttpSession session) {
        log.info("deletePro id: {}", email);
        log.info("deletePro pw: {}", pw);
        // 탈퇴처리
        // email와 비번 맞는지 확인
        MemberDTO memberDTO = memberMapper.emailPwCheck(email, pw);
        boolean result = false;
        if (memberDTO != null) {
            // 맞으면 -> 탈퇴 처리 후 -> 결과 화면에 전달
            result = true;
            memberMapper.deleteMember(email); // 탈퇴
            session.invalidate();  // 로그아웃 처리
        }
        // 틀리면 -> 결과만 화면에 전달
        model.addAttribute("result", result);
        return "members/deletePro";
    }

    // id 중복확인 팝업 요청
    @GetMapping("/idAvail")
    public String idAvail(String id, Model model) {
        // 전달받은 id값이 DB에 존재하는지 여부 판단해서 view에 결과 전달
        boolean result = true; // 초기값은 사용가능으로 넣어놓고 밑에서 판단
        MemberDTO memberDTO = memberMapper.selectOne(id);
        if (memberDTO != null) {
            result = false; // 이미 사용중 -> 사용 불가
        }
        // 이미 사용중이면 false, 사용가능한 id면 true
        model.addAttribute("result", result);

        return "members/idAvail";
    }

    // id 중복 ajax 요청 --> 리턴은 결과 데이터 (html X)
    @PostMapping("/idAvailAjax")
    @ResponseBody  // -> HTML 화면결과가 아닌, 데이터를 응답하겠다~~
    public String idAvailAjax(String id) {
        log.info("Ajax id: {}", id);
        String result = "사용가능합니다";
        MemberDTO memberDTO = memberMapper.selectOne(id);
        if (memberDTO != null) {
            result = "이미사용중입니다..";
        }
        return result; // 문자열자체를 응답
    }
}
