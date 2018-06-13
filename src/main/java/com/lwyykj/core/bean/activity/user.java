package com.lwyykj.core.bean.activity;

import java.io.Serializable;

public class user implements Serializable {
    private Short auid;

    private String auser;

    private String apass;

    private String aname;

    private String amail;

    private String atel;

    private Boolean level;

    private Short gid;

    private String pclasstype;

    private static final long serialVersionUID = 1L;

    public Short getAuid() {
        return auid;
    }

    public void setAuid(Short auid) {
        this.auid = auid;
    }

    public String getAuser() {
        return auser;
    }

    public void setAuser(String auser) {
        this.auser = auser == null ? null : auser.trim();
    }

    public String getApass() {
        return apass;
    }

    public void setApass(String apass) {
        this.apass = apass == null ? null : apass.trim();
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getAmail() {
        return amail;
    }

    public void setAmail(String amail) {
        this.amail = amail == null ? null : amail.trim();
    }

    public String getAtel() {
        return atel;
    }

    public void setAtel(String atel) {
        this.atel = atel == null ? null : atel.trim();
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public Short getGid() {
        return gid;
    }

    public void setGid(Short gid) {
        this.gid = gid;
    }

    public String getPclasstype() {
        return pclasstype;
    }

    public void setPclasstype(String pclasstype) {
        this.pclasstype = pclasstype == null ? null : pclasstype.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", auid=").append(auid);
        sb.append(", auser=").append(auser);
        sb.append(", apass=").append(apass);
        sb.append(", aname=").append(aname);
        sb.append(", amail=").append(amail);
        sb.append(", atel=").append(atel);
        sb.append(", level=").append(level);
        sb.append(", gid=").append(gid);
        sb.append(", pclasstype=").append(pclasstype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}