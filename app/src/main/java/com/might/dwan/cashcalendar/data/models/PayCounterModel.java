package com.might.dwan.cashcalendar.data.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Might on 25.08.2017.
 */

public class PayCounterModel implements Serializable {
    private String user_id;
    private int category;
    private String category_text;
    private int subcategory;
    private String subcategory_text;
    private String description;
    private String count_pay;
    private String timestamp;
    private String pay_item_id;

    public PayCounterModel(String user_id, int category, String category_text, int subcategory, String subcategory_text, String description, String count_pay, String timestamp) {
        this.user_id = user_id;
        this.category = category;
        this.category_text = category_text;
        this.subcategory = subcategory;
        this.subcategory_text = subcategory_text;
        this.description = description;
        this.count_pay = count_pay;
        this.timestamp = timestamp;
        this.pay_item_id = UUID.randomUUID().toString();
    }


    public PayCounterModel() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount_pay() {
        return count_pay;
    }

    public void setCount_pay(String count_pay) {
        this.count_pay = count_pay;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPay_item_id() {
        return pay_item_id;
    }

    public void setPay_item_id(String pay_item_id) {
        this.pay_item_id = pay_item_id;
    }

    public String getCategory_text() {
        return category_text;
    }

    public void setCategory_text(String category_text) {
        this.category_text = category_text;
    }

    public String getSubcategory_text() {
        return subcategory_text;
    }

    public void setSubcategory_text(String subcategory_text) {
        this.subcategory_text = subcategory_text;
    }
}
