package com.might.dwan.cashcalendar.data.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Might on 25.08.2017.
 */

public class CostItem implements Serializable {
    private String user_id;
    private int category;
    private String category_text;
    private int subcategory;
    private String subcategory_text;
    private String description;
    private String count_pay;
    private String timestamp;
    private String pay_item_id;

    public CostItem(String user_id, int category, String category_text, int subcategory,
                    String subcategory_text, String description, String count_pay, String timestamp) {
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


    public CostItem() {
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
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

    public String getCountPay() {
        return count_pay;
    }

    public void setCountPay(String count_pay) {
        this.count_pay = count_pay;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPayItemId() {
        return pay_item_id;
    }

    public void setPayItemId(String pay_item_id) {
        this.pay_item_id = pay_item_id;
    }

    public String getCategoryText() {
        return category_text;
    }

    public void setCategoryText(String category_text) {
        this.category_text = category_text;
    }

    public String getSubcategoryText() {
        return subcategory_text;
    }

    public void setSubcategoryText(String subcategory_text) {
        this.subcategory_text = subcategory_text;
    }
}
