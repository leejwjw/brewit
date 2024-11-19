package com.pt.brewit.mapper;

import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 관리자 매퍼
@Mapper
public interface AdminMapper {
    List<MemberDTO> selectAllMember();

    // 특정 회원 정보 조회
    MemberDTO selectMemberById(int id);

    // 특정 회원 삭제
    void deleteMemberById(int id);
    // 특정 회원 수정
    void updateMemberById(MemberDTO memberDTO);
}
