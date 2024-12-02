package com.pt.brewit.controller.main;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.dto.SellerDTO;
import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.security.domain.CustomUser;
import com.pt.brewit.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 구매 목록 페이지 요청
    @GetMapping ("/payments/{email}")
    public String payments(@PathVariable("email") String username, Model model) {
        log.info("GET /payments 구매 목록 요청!");
        log.info("payments username: {}", username);

        MemberDTO member = memberService.getMember(username);
        log.info("payments member: {}", member);
        int memberId = member.getMember_id();
        model.addAttribute("member", member);

        // DB에서 구매전체목록가져와 화면에 전달
        List<PaymentDTO> lists = memberService.getPayments(memberId);
        log.info("payments lists: {}", lists);
        model.addAttribute("lists", lists);

        return "/main/members/paylists";
    }
    // 구매 목록 환불 요청
    @PostMapping("/paymentRefund/{payment_id}")
    @ResponseBody
    public String paymentRefund(@PathVariable("payment_id") int payment_id){
        log.info("paymentRefund payment_id: {}", payment_id);
        // 환불 신청 처리
        String status = "refunded";
        int result = memberService.updatePayment(payment_id,status);
        return "ok";
    }

    // 회원 가입 폼 요청
    @GetMapping("/new")
    public String newForm(@ModelAttribute("member") MemberDTO member) {
        return "/main/members/newForm";
    }

    // 회원가입 처리 요청
    @PostMapping("/new")
    public String newPro(MemberDTO member, String au,Model model) {
        log.info("newPro - member : {}", member);
        log.info("newPro - au : {}", au);
        // 회원 가입 처리
        int result = memberService.register(member, au);
        log.info("newPro - result : {}", result);
        String varify = "회원가입이 완료 되었습니다";
        String messgae = " 님의 회원가입을 축하합니다. 알차고 실속있는 서비스로 찾아 뵙겠습니다.";
        String name = member.getName();
        model.addAttribute("varify",varify);
        model.addAttribute("name", name);
        model.addAttribute("messgae", messgae);
        log.info("newPro - model : {}", model);

        return "/main/members/newCon"; // 페이지 이동 = "/" 경로 코드로 요청 -> @..Mapping("/") 메소드호출
    }

    // 회원 판매자전환 폼 요청
    @GetMapping("/trans/{email}")
    public String transForm(@PathVariable("email") String username, Model model , RedirectAttributes rttr) {
        log.info("trans username: {}", username);

        SellerDTO seller = memberService.getSeller(username);
        log.info("trans seller: {}", seller);

        int sellerMemberId = seller.getMember_id();
        log.info("trans sellerMemberId: {}", sellerMemberId);

        SellerDTO sellerId = memberService.getSellerId(sellerMemberId);
        log.info("trans sellerId: {}", sellerId);

        if (sellerId != null) {
            rttr.addAttribute("sellerID",true);
            return "redirect:/";
        }

        model.addAttribute("seller", seller);

        log.info("trans model: {}", model);

        return "/main/members/transForm";
    }

    // 판매자전환 처리 요청
    @PostMapping("/trans/{member_id}")
    public String newTrans(@PathVariable("member_id") String member_id, Model model, MemberDTO member,SellerDTO seller) {
        log.info("newTrans - member_id : {}", member_id);
        log.info("newTrans - member : {}", member);
        log.info("newTrans - seller : {}", seller);

         // 판매자전환 처리
        //seller.setStatus("inactive");
        //memberService.updateSeller(member);
        int result = memberService.transRegister(seller);
        log.info("newTrans - result : {}", result);

        String messgae = "판매자 전환신청 정보가 완료되었습니다.";
        String name = member.getName();
        String email = member.getEmail();
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("messgae", messgae);
        log.info("newTrans - model : {}", model);

        return "/main/members/modifyCon"; // 페이지 이동 = "/" 경로 코드로 요청 -> @..Mapping("/") 메소드호출
        //return "redirect:/";
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
    public String modify(@PathVariable("email") String username, MemberDTO member,Model model){
        log.info("modifyPage username: {}", username);
        log.info("modifyPage member: {}", member);
        member.setName(member.getName());
        member.setCellphone(member.getCellphone());
        member.setTelephone(member.getTelephone());
        member.setAddress1(member.getAddress1());
        member.setAddress2(member.getAddress2());
        memberService.updateMember(member);

        String messgae = " 님의 회원정보가 수정 되었습니다.";
        String name = member.getName();
        String email = member.getEmail();
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("messgae", messgae);
        log.info("newPro - model : {}", model);

        return "/main/members/modifyCon"; // 페이지 이동 = "/" 경로 코드로 요청 -> @..Mapping("/") 메소드호출

        //return "redirect:/main/{email}";
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
        String result = "사용 가능한 이메일입니다.";
        MemberDTO memberDTO = memberMapper.selectOne(email);
        if (memberDTO != null) {
            result = "이미 사용중인 이메일입니다.";
        }
        return result; // 문자열자체를 응답
    }
}
