package com.pt.brewit.service;

import com.pt.brewit.dto.MemberDTO;
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

//    @Override
//    public List<MemberDTO> getBoardListWithPaging(PagerDTO pager) {
//        List<MemberDTO> list = adminMapper.findListWithPaging(pager);
//        return list;
//    }
}
