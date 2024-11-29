package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.dto.SellerDTO;

import java.util.List;

public interface MemberService {
    // 회원 가입
    public int register(MemberDTO member, String au);
    // 판매자전환 가입
    public int transRegister(SellerDTO seller);
    // 회원 1명 조회
    public MemberDTO getMember(String username);
    // 회원 1명 member_id 조회
    public SellerDTO getSeller(String username);
    // 회원 1명 member_id 조회 getSellerId
    public SellerDTO getSellerId(int memberId);
    // 구매 목록 조회
    public List<PaymentDTO> getPayments(int memberId);
    // 회원 정보 수정
    public int updateMember(MemberDTO member);
    // 회원 정보 상태수정
    public int updateSeller(MemberDTO member);
    // 회원 삭제
    public int deleteMember(String username);
    //구매환불처리
    int updatePayment(int payment_id, String status);
}
