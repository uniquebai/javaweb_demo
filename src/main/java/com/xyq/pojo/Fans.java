package com.xyq.pojo;

/**
 * @author cwtt
 */
public class Fans {
    private Integer id;
    private Integer uId;
    private Integer fId;

    public Fans() {
    }

    public Fans(Integer id, Integer uId, Integer fId) {
        this.id = id;
        this.uId = uId;
        this.fId = fId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @Override
    public String toString() {
        return "Fans{" +
                "id=" + id +
                ", uId=" + uId +
                ", fId=" + fId +
                '}';
    }
}
