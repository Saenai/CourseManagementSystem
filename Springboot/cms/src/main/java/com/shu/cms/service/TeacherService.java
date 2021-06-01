package com.shu.cms.service;

import java.util.List;

import com.shu.cms.entity.TeacherEntity;

public interface TeacherService {
    List<TeacherEntity> getRows();

    int insertRow(TeacherEntity entity);

    int updateRow(TeacherEntity entity);

    int deleteRow(String id);
}
