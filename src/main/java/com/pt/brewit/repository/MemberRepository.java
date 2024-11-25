package com.pt.brewit.repository;

import com.pt.brewit.dto.AuthDTO;
import com.pt.brewit.dto.MemberDTO;

import java.util.List;

public interface MemberRepository {

    // 회원 저장
    public int save(MemberDTO member);
    // 권한 추가
    public int addAuth(AuthDTO auth);
    // 회원 1명 조회
    public MemberDTO findByUsername(String username);
    // 회원 목록 조회
    public List<MemberDTO> findAll();
    // 회원 정보 수정
    public int updateMember(MemberDTO member);
    // 회원 정보 삭제
    public int deleteMember(String username);

}
