package com.pt.brewit.security.domain;

import com.pt.brewit.dto.MemberDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter // MemberDTO 외부에서 꺼낼 수 있게 Getter 추가
public class CustomUser extends User { // == Principal

    private MemberDTO member; // 우리가 아는 회원 정보 DTO 추가

    // User 부모 클래스에 기본생성자 X -> 부모 생성자 호출하는 자식 생성자 작성
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    // 외부에서 MemberDTO를 매개변수로 주면, 부모생성자 호출하며 시큐리티용 데이터 세팅, MemberDTO도 체우기 
    public CustomUser(MemberDTO member) {
        super(member.getEmail(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority(member.getAuth()))
        );
        // AS IS : Auth 1,2,3 -> TO BE : Auth 를 문자열로 변경 ex. ROLE_MEMBER, ROLE_SELLER, ROLE_ADMIN -> DB 컬럼 varchar
        this.member = member;
    }

}
