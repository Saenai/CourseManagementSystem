package com.shu.cms.mapper;

import com.shu.cms.entity.AdminEntity;

import org.apache.ibatis.annotations.Mapper;
/*
*   Mapper just for testing
*   @author Saenai
*/

@Mapper
public interface TestMapper {
    AdminEntity selectEntityById(String id);
}