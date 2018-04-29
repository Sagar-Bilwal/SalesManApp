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
    private String date;
    private String name;
    private String taskNo;

    public TaskNo()
    {

    }
    public TaskNo(String companyName, String address, String contact)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
    }

    public TaskNo(String companyName, String address, String contact,String status,String reason,String date)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.reason = reason;
        this.date = date;
    }

    public TaskNo(String companyName, String address, String contact,String status,String reason,String date,String taskNo)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.reason = reason;
        this.date = date;
        this.taskNo = taskNo;
    }
    public TaskNo(String companyName, String address, String contact,String status,String reason,String date,String name,String taskNo)
    {
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.reason = reason;
        this.date = date;
        this.name = name;
        this.taskNo = taskNo;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
}
