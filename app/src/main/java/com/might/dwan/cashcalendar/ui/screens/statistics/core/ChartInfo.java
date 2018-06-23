package com.might.dwan.cashcalendar.ui.screens.statistics.core;

public class ChartInfo {
    private String title;
    private float value;

    public ChartInfo(String title, float value) {
        this.title = title;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
