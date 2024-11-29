package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.dto.PaymentDTO;
import com.pt.brewit.dto.SellerDTO;
import com.pt.brewit.mapper.MemberMapper;
import com.pt.brewit.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper; // 위 리포지토리 대신 Mapper로 바로 변경
    private final PasswordEncoder passwordEncoder;

    @Override
    public int register(MemberDTO member, String au) {
        //비밀번호 암호화*
        String encodedPw = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPw);//암호화된 비밀번호로 변경
        member.setAuth("member");

        //회원정보 저장
        int memberRes = memberRepository.save(member);
        //int memberRes = memberMapper.insertMember(member);
        return (memberRes == 1 ) ? 1 : 0;
    }

    @Override
    public int transRegister(SellerDTO seller) {
        seller.setMember_id(seller.getMember_id());
        seller.setCompany_name(seller.getCompany_name());
        seller.setB_number(seller.getB_number());
        seller.setStatus(seller.getStatus());
        int transMemberRes = memberRepository.transSave(seller);
        return (transMemberRes == 1 ) ? 1 : 0;
    }

    @Override
    public MemberDTO getMember(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public SellerDTO getSeller(String username) {return memberRepository.findBySellerUsername(username);
    }
    @Override
    public SellerDTO getSellerId(int memberId) {return memberRepository.findBySellerId(memberId);
    }

    @Override
    public List<PaymentDTO> getPayments(int memberId) { return memberRepository.findAll(memberId);}

    @Override
    public int updateMember(MemberDTO member) {
        return memberRepository.updateMember(member);
    }
    @Override
    public int updateSeller(MemberDTO member) {
        return memberRepository.updateSeller(member);
    }

    // TODO : 삭제 처리
    @Override
    public int deleteMember(String username) {
        memberRepository.deleteMember(username);
        int result = memberRepository.deleteMember(username);
        return result;
    }
}
