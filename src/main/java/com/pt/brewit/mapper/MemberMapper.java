package com.pt.brewit.mapper;

import com.pt.brewit.dto.AuthDTO;
import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 회원가입
    public int insertMember(MemberDTO member);

    // 회원 권한 추가
    public int insertAuth(AuthDTO auth);

    // 회원 1명 조회
    public MemberDTO selectMemberByUsername(String username);

    // 회원 목록 조회
    public List<MemberDTO> selectAllMembers();
    // 회원 정보 수정
    public int updateMember(MemberDTO member);
    // 회원 정보 수정 삭제
    public int deleteMember(String username);
    // 권한 정보 수정 삭제
    public void deleteAuth(String username);





    // 회원 전체 조회
    List<MemberDTO> selectAll();
    // id로 회원 1명 조회
    MemberDTO selectOne(String id);
    // 회원 정보 수정
    //void updateMember(MemberDTO member);
    // 회원 삭제
    //void deleteMember(String id);
    // email, pw 일치 확인 (login)
    MemberDTO emailPwCheck(@Param("email") String email, @Param("pw") String pw);


}
