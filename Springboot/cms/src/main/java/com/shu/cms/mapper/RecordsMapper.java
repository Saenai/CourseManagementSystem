package com.shu.cms.mapper;

import java.util.List;

import com.shu.cms.entity.RecordsEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordsMapper {
    RecordsEntity selectEntityById(String id);

    List<RecordsEntity> selectEntity();

    int insertEntity(RecordsEntity entity);

    int updateEntity(RecordsEntity entity);

    int deleteEntityById(String id);
}
