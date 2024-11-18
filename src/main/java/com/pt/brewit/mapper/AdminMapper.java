package com.pt.brewit.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.pt.brewit.dto.admin.MemberDTO;


// 관리자 매퍼
@Mapper
public interface AdminMapper {
    public int selectMember(MemberDTO member);
}
