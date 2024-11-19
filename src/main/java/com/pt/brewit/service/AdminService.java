package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {

    //전체 회원 목록 조회
    public List<MemberDTO> getMemberList();
    // 전체 회원 목록 조회 + paging
//    public List<MemberDTO> getMemberListWithPaging(PagerDTO pager);
    //회원 찾기
    public MemberDTO getMemberById(int id);
    //회원 삭제
    public void deleteMember(int id);
    //회원 수정
    public void updateMember(int id, MemberDTO memberDTO);
}
