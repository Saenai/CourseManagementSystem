package com.shu.cms.mapper;

import java.util.List;

import com.shu.cms.entity.AdminEntity;

import org.apache.ibatis.annotations.Mapper;
/*
*   Mapper just for testing
*   @author Saenai
*/

@Mapper
public interface AdminMapper {
    AdminEntity selectEntityById(String id);

    List<AdminEntity> selectEntity();

    int insertEntity(AdminEntity adminEntity);
}
