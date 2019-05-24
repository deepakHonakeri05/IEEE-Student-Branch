package com.pro.deepak.ieee_ex.data;

public class dataElement {

    private String title,desc,link,cont1,cont2,cont3,cont4;

    public dataElement()
    {

    }

    public dataElement(String title,String desc,String link,String cont1,String cont2,String cont3,String cont4)
    {
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.cont1 = cont1;
        this.cont2 = cont2;
        this.cont3 = cont3;
        this.cont4 = cont4;

    }

    public String getTitle() {
        return title;
    }

    public void setTitleD(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCont1() {
        return cont1;
    }

    public void setCont1(String cont1) {
        this.cont1 = cont1;
    }

    public String getCont2() {
        return cont2;
    }

    public void setCont2(String cont2) {
        this.cont2 = cont2;
    }

    public String getCont3() {
        return cont3;
    }

    public void setCont3(String cont3) {
        this.cont3 = cont3;
    }

    public String getCont4() {
        return cont4;
    }

    public void setCont4(String cont4) {
        this.cont4 = cont4;
    }
}
