package com.shu.cms.service;

import java.util.List;

import com.shu.cms.entity.RecordsEntity;

public interface RecordsService {
    RecordsEntity getRowById(String id);

    List<RecordsEntity> getRows();

    int insertRow(RecordsEntity entity);

    int updateRow(RecordsEntity entity);

    int deleteRow(String id);
}
