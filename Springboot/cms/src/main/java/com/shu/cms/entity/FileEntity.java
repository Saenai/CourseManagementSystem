package com.shu.cms.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FileEntity {
    String originname;
    String serverpath;
    long size;

    public String getOriginname() {
        return this.originname;
    }

    public void setOriginname(String originname) {
        this.originname = originname;
    }

    public String getServerpath() {
        return this.serverpath;
    }

    public void setServerpath(String serverpath) {
        this.serverpath = serverpath;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
