package com.liao.pojo;

public class Admin {

  private Integer eid;
  private String ename;
  private String epassword;

  public Integer getEid() {
    return eid;
  }

  public void setEid(Integer eid) {
    this.eid = eid;
  }

  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public String getEpassword() {
    return epassword;
  }

  public void setEpassword(String epassword) {
    this.epassword = epassword;
  }

  @Override
  public String toString() {
    return "Admin{" +
      "eid=" + eid +
      ", ename='" + ename + '\'' +
      ", epassword='" + epassword + '\'' +
      '}';
  }
}
