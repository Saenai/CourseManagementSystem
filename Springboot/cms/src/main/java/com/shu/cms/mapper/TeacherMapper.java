package com.shu.cms.mapper;

import java.util.List;

import com.shu.cms.entity.TeacherEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherEntity selectEntityById(String id);

    List<TeacherEntity> selectEntity();

    int insertEntity(TeacherEntity adminEntity);

    int updateEntity(TeacherEntity adminEntity);

    int deleteEntityById(String id);
}
