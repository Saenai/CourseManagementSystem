package com.shu.cms.entity;
/*  
*   AdminEntity
*   @Author： Saenai
*/

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminEntity {
    private String id;
    private String name;
    private String pw;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return this.pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
