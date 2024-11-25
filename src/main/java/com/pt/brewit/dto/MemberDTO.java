package com.pt.brewit.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class MemberDTO {
    private int member_id;           // 회원아이디
    private String name;            // 회원이름
    private String password;        // 비밀번호
    private String cellphone;       // 휴대전화번호
    private String telephone;       // 전화번호
    private String address1;        // 주소1
    private String address2;        // 세부주소
    private String email;           // 이메일
    private String auth;               // 권한
    private int is_subscribe;        // 구독여부
    private int bank_id;             // 은행아이디
    private String bank_account;     // 계좌번호
    private String is_kakao;         // 소셜로그인 구분 컬럼
    private Date reg_date;           // 회원가입일
    private String status;          // 상태 (active, inactive)

}
