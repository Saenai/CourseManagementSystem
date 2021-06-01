package com.shu.cms.mapper;

import java.util.List;

import com.shu.cms.entity.StudentEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    StudentEntity selectEntityById(String id);

    List<StudentEntity> selectEntity();

    int insertEntity(StudentEntity entity);

    int updateEntity(StudentEntity entity);

    int deleteEntityById(String id);
}
