package com.example.demo.mapper;

import com.example.demo.model.test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface testMapper {
    @Select("select (#{id},#{name}) from course_management")
    String select(test a);
}
