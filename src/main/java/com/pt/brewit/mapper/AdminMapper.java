package com.pt.brewit.mapper;

import com.pt.brewit.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


// 관리자 매퍼
@Mapper
public interface AdminMapper {
    List<MemberDTO> selectAllMember();
}
