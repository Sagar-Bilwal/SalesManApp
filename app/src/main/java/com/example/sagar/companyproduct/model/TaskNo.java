package com.example.sagar.companyproduct.model;

import java.util.jar.Attributes;

/**
 * Created by SAGAR on 27-04-2018.
 */

public class TaskNo
{
    private String companyName;
    private String address;
    private String contact;
    private String status;
    private String reason;
    public TaskNo()
    {

    }
    public TaskNo(String companyName, String address, String contact)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
    }

    public TaskNo(String companyName, String address, String contact,String status,String reason)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.reason = reason;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
