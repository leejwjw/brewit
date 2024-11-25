package com.pt.brewit.security;

import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.repository.MemberRepository;
import com.pt.brewit.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO findMember = memberRepository.findByUsername(username);
        if(findMember == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(findMember); // UserDetails 인터페이스 <- User 구현 클래스 <- CustomUser 자식
    }
}
