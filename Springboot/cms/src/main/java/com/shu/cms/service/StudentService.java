package com.shu.cms.service;

import java.util.List;

import com.shu.cms.entity.StudentEntity;

public interface StudentService {
    List<StudentEntity> getRows();

    int insertRow(StudentEntity entity);

    int updateRow(StudentEntity entity);

    int deleteRow(String id);

    StudentEntity getRowById(String id);

    List<StudentEntity> getRowsOfIdName();
}
