package io.guozhihong.demo.model;


public class TableModel {
    private long id;
    private long sid;
    private long cid;

    public TableModel() {
    }

    public TableModel(long sid, long cid) {
        this.sid = sid;
        this.cid = cid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
}
