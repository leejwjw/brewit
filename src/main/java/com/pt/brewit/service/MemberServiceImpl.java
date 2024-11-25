package com.pt.brewit.service;

import com.pt.brewit.dto.AuthDTO;
import com.pt.brewit.dto.MemberDTO;
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

        //int authRes = 0;
        //권한부여 (MEMBER)
        //AuthDTO authDTO = new AuthDTO();
        //member.setEmail(member.getEmail());
        //member.setAuth("ROLE_MEMBER");
        //authRes = memberRepository.addAuth(authDTO);
        //권한 추가 부여 (ADMIN)
        //if(au.equals("ADMIN")){
        //    authDTO.setAuth("ROLE_ADMIN");
        //    authRes = memberRepository.addAuth(authDTO);
        //}
        //return (memberRes == 1 && authRes == 1) ? 1 : 0;
        if(au.equals("admin")){
            member.setAuth("admin");
        }else if(au.equals("member")){
            member.setAuth("member");
        }else{
            member.setAuth("seller");
        }

        //회원정보 저장
        int memberRes = memberRepository.save(member);
        //int memberRes = memberMapper.insertMember(member);
        return (memberRes == 1 ) ? 1 : 0;
    }

    @Override
    public MemberDTO getMember(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public List<MemberDTO> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public int updateMember(MemberDTO member) {
        return memberRepository.updateMember(member);
    }

    // TODO : 삭제 처리
    @Override
    public int deleteMember(String username) {
        memberRepository.deleteMember(username);
        int result = memberRepository.deleteMember(username);
        return result;
    }
}
