package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.SellerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pt.brewit.mapper.AdminMapper;

import java.util.List;

@Service  // Service 기능을 하는 스프링빈이다~
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public List<MemberDTO> getMemberList() {
        List<MemberDTO> list = adminMapper.selectAllMember();
        return list;
    }

    @Override
    public List<SellerDTO> getSellerConfrimList() {
        List<SellerDTO> list = adminMapper.selectSellerConfrimList();
        return list;
    }
    @Override
    public void confirmSeller(int id) {
        adminMapper.confirmSellerMember(id);
    }
    @Override
    public void updateSeller(int id) { adminMapper.updateSellerMember(id);}

    // 특정 회원 정보 가져오기
    @Override
    public MemberDTO getMemberById(int id) {
        log.info("Fetching member with id: {}", id);
        return adminMapper.selectMemberById(id); // Mapper에서 회원 정보 가져오기
    }

    // 회원 삭제
    @Override
    public void deleteMember(int id) {
        log.info("Deleting member with id: {}", id);
        log.info("삭제과정!!!!!!!!!!!!!!!!!!!!!!!!!!");
        adminMapper.deleteMemberById(id); // Mapper에서 회원 삭제
    }
    @Override
    public void updateMember(int id, MemberDTO member) {
        log.info("수정과정 !!!!!!!");
        member.setMember_id(id);
        log.info(member.toString());
        adminMapper.updateMemberById(member);
    }
}
