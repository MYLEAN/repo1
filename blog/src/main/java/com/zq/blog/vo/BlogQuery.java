package com.zq.blog.vo;

public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean reconmmend;

    public BlogQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isReconmmend() {
        return reconmmend;
    }

    public void setReconmmend(boolean reconmmend) {
        this.reconmmend = reconmmend;
    }
}
