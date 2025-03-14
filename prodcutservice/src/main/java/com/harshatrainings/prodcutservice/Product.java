package com.harshatrainings.prodcutservice;

public class Product {

    private Integer pid;
    private String pname;
    private Integer price;

    public Product(){

    }

    public Product(Integer pid, String pname, Integer price) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
