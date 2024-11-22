package com.pt.brewit.mapper;

import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 관리자 매퍼
@Mapper
public interface AdminMapper {
    //전체 회원 조회
    List<MemberDTO> selectAllMember();
    //판매자 승인 목록 조회
    List<MemberDTO> selectSellerConfrimList();
    // 판매자 승인처리
    void confirmSellerMember(int id);
    // 특정 회원 정보 조회
    MemberDTO selectMemberById(int id);
    // 특정 회원 삭제
    void deleteMemberById(int id);
    // 특정 회원 수정
    void updateMemberById(MemberDTO memberDTO);
}
