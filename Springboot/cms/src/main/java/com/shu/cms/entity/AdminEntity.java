package com.shu.cms.entity;
/*  
*   AdminEntity
*   @Author： Saenai
*/

import com.shu.cms.service.EncryptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminEntity {
    private String id;
    private String name;
    private String pw;
    @Autowired
    EncryptService encryptService;

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
