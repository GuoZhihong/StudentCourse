package io.guozhihong.demo.model;


public class DemoModelStudent {

    private long sid;

    private String sName;
    private String sSex;
    private int sAge;

    public DemoModelStudent() {
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }
}
