package com.shu.cms.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class RecordsEntity {
    String id;
    int w1;
    int w2;
    int w3;
    int w4;
    int w5;
    int w6;
    int w7;
    int w8;
    int w9;
    int w10;
    int score;
    int final_score;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getW1() {
        return this.w1;
    }

    public void setW1(int w1) {
        this.w1 = w1;
    }

    public int getW2() {
        return this.w2;
    }

    public void setW2(int w2) {
        this.w2 = w2;
    }

    public int getW3() {
        return this.w3;
    }

    public void setW3(int w3) {
        this.w3 = w3;
    }

    public int getW4() {
        return this.w4;
    }

    public void setW4(int w4) {
        this.w4 = w4;
    }

    public int getW5() {
        return this.w5;
    }

    public void setW5(int w5) {
        this.w5 = w5;
    }

    public int getW6() {
        return this.w6;
    }

    public void setW6(int w6) {
        this.w6 = w6;
    }

    public int getW7() {
        return this.w7;
    }

    public void setW7(int w7) {
        this.w7 = w7;
    }

    public int getW8() {
        return this.w8;
    }

    public void setW8(int w8) {
        this.w8 = w8;
    }

    public int getW9() {
        return this.w9;
    }

    public void setW9(int w9) {
        this.w9 = w9;
    }

    public int getW10() {
        return this.w10;
    }

    public void setW10(int w10) {
        this.w10 = w10;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFinal_score() {
        return this.final_score;
    }

    public void setFinal_score(int final_score) {
        this.final_score = final_score;
    }
}
