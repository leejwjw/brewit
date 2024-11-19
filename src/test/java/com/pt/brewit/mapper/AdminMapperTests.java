package com.pt.brewit.mapper;

import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import com.pt.brewit.dto.MemberDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@SpringBootTest
@Slf4j

public class AdminMapperTests {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test() {
        log.info("adminMapper: {}", adminMapper);
    }

    @Test
    public void testFindAll() {
        List<MemberDTO> list = adminMapper.selectAllMember();

        // Java Stream
        //list.forEach(boardDTO -> {log.info(boardDTO.toString());});
        for (MemberDTO dto : list) {
            log.info("dto: {}", dto.toString());
        }
    }

}
