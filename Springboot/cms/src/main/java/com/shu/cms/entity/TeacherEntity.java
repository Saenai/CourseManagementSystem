package com.shu.cms.entity;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TeacherEntity {
    private String id;
    private String name;
    private String pw;
    private Date dateA;
    private Date dateB;

    public Date getDateA() {
        return this.dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public Date getDateB() {
        return this.dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

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

    // public String getPw() {
    // return this.pw;
    // }

    public String getPw() {
        // System.out.println("A" + this.pw);
        // if (this.id.equals("zs")==false || this.id.equals("ls") == false ||
        // this.id.equals("ww"))==false {
        // this.pw = encryptService.decrypt(this.pw);
        // }
        // ;
        // System.out.println("B" + this.pw);
        return this.pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
