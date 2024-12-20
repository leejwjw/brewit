package com.pt.brewit.mapper;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.Pager;
import com.pt.brewit.dto.SellerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

// 관리자 매퍼
@Mapper
public interface AdminMapper {
    //전체 회원 조회
    List<MemberDTO> selectAllMember();
    //판매자 승인 목록 조회
    List<SellerDTO> selectSellerConfrimList();
    // 판매자 승인처리
    void confirmSellerMember(int id);

    void updateSellerMember(int id);
    // 특정 회원 정보 조회
    MemberDTO selectMemberById(int id);
    // 특정 회원 삭제
    void deleteMemberById(int id);
    // 특정 회원 수정
    void updateMemberById(MemberDTO memberDTO);
    // 메인 count
    int SelectCountMember();
    int SelectCountProduct();
    int SelectCountOrder();
    int SelectCountPayment();
    int SelectCountTermEvent();
    int SelectCountToday();

    //메인 차트

    List<Map<String, Object>> selectMonthlyMemberCount(int year);
    List<Map<String, Object>> selectMonthlyOrderCount(@Param("year") int year,@Param("logged_member") MemberDTO logged_member);
    List<Map<String, Object>> selectMonthlyTermEventCount(@Param("year") int year,@Param("logged_member") MemberDTO logged_member);

}
