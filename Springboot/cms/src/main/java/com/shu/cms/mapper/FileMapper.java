package com.shu.cms.mapper;

import java.util.List;

import com.shu.cms.entity.FileEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    FileEntity selectEntityById(String id);

    List<FileEntity> selectEntity();

    int insertEntity(FileEntity entity);

    int updateEntity(FileEntity entity);

    int deleteEntityById(String id);

    List<FileEntity> selectEntityWithoutPath();
}
