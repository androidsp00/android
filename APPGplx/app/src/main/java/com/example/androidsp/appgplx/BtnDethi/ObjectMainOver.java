package com.example.androidsp.appgplx.BtnDethi;

/**
 * Created by Cheng Lee on 11/1/2016.
 */
public class ObjectMainOver {

    /**
     * 1 - trả lời đúng
     * 2 - trả lời sai
     * 3 - không trả lời
     */

    public int checkQues;
    public int index;

    public ObjectMainOver() {
    }

    public ObjectMainOver(int index, int checkQues) {
        this.index = index;
        this.checkQues = checkQues;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCheckQues() {
        return checkQues;
    }

    public void setCheckQues(int checkQues) {
        this.checkQues = checkQues;
    }
}
