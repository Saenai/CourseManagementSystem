package com.shu.cms.service;

import java.util.List;

import com.shu.cms.entity.RecordsEntity;
import com.shu.cms.entity.RecordsEntityEx;

public interface RecordsService {
    RecordsEntity getRowById(String id);

    List<RecordsEntity> getRows();

    List<RecordsEntityEx> getRowsWithName();

    int insertRow(RecordsEntity entity);

    int updateRow(RecordsEntity entity);

    int deleteRow(String id);
}
