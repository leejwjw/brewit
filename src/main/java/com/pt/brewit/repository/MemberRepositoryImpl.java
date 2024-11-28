package com.pt.brewit.repository;

import com.pt.brewit.dto.AuthDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.SellerDTO;
import com.pt.brewit.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Repository 역할을 하는 클래스로, 스프링 빈으로 등록
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public int save(MemberDTO member) {
        return memberMapper.insertMember(member);
    }

    @Override
    public int transSave(SellerDTO seller) {
        return memberMapper.insertSeller(seller);
    }


    @Override
    public int addAuth(AuthDTO auth) {
        return memberMapper.insertAuth(auth);
    }

    @Override
    public MemberDTO findByUsername(String username) {

        log.info("repositoryImpl findByUsername - username: {}", username);
        MemberDTO memberDTO = memberMapper.selectMemberByUsername(username);
        log.info("repositoryImpl findByUsername - memberDTO: {}", memberDTO);
        return memberDTO;
    }

    @Override
    public SellerDTO findBySellerUsername(String username) {

        log.info("repositoryImpl findBySellerUsername - username: {}", username);
        SellerDTO sellerDTO = memberMapper.selectSellerByUsername(username);
        log.info("repositoryImpl findBySellerUsername - sellerDTO: {}", sellerDTO);
        return sellerDTO;
    }
    @Override
    public SellerDTO findBySellerId(int memberId) {

        log.info("repositoryImpl findBySellerId - memberId: {}", memberId);
        SellerDTO sellerDTO = memberMapper.selectSellerByMemberId(memberId);
        log.info("repositoryImpl findBySellerId - sellerDTO: {}", sellerDTO);
        return sellerDTO;
    }

    @Override
    public List<MemberDTO> findAll() {
        return memberMapper.selectAllMembers();
    }

    @Override
    public int updateMember(MemberDTO member) {
        return memberMapper.updateMember(member);
    }
    @Override
    public int updateSeller(MemberDTO member) {
        return memberMapper.updateSeller(member);
    }

    @Override
    public int deleteMember(String username) {
        return memberMapper.deleteMember(username);
    }

}
