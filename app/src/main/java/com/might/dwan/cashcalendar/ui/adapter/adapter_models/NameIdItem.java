package com.might.dwan.cashcalendar.ui.adapter.adapter_models;

/**
 * Created by Might on 02.09.2017.
 */

public class NameIdItem {
    private String name;
    private int id;

    public NameIdItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public NameIdItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
