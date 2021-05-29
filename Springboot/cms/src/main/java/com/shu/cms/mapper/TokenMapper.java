package com.shu.cms.mapper;

import com.shu.cms.entity.TokenEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    TokenEntity selectEntityToken(String token);

    int insertEntity(TokenEntity tokenEntity);
}
