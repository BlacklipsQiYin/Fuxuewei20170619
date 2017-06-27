package com.bawei.fuxuewei20170619;

public class CheckBean {

    public String content;
    //标记checkbox 是否选中
    public boolean ischeck;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    @Override
    public String toString() {
        return "CheckBean{" +
                "content='" + content + '\'' +
                ", ischeck=" + ischeck +
                '}';
    }
}