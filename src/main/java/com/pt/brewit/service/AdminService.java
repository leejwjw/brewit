package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.SellerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {

    //전체 회원 목록 조회
    public List<MemberDTO> getMemberList();
    // 판매자 승인전 목록
    public List<SellerDTO> getSellerConfrimList();
    // 판매자 승인 처리
    public void confirmSeller(int id);
    public void updateSeller(int id);
    //회원 찾기
    public MemberDTO getMemberById(int id);
    //회원 삭제
    public void deleteMember(int id);
    //회원 수정
    public void updateMember(int id, MemberDTO memberDTO);

    //admin 메인 카운트
    public int getMemberCount();
    public int getProductCount();
    public int getTermEventCount();
    public int getOrderCount();
    public int getPaymentCount();
    public int getTodayCount();
}
