package com.shu.cms.mapper;

import com.shu.cms.entity.TokenEntity;

import org.apache.ibatis.annotations.Mapper;
/*
*   Mapper just for testing
*   @author Saenai
*/

@Mapper
public interface TokenMapper {
    TokenEntity selectEntityToken(String token);

    Void insertEntity(TokenEntity tokenEntity);
}
