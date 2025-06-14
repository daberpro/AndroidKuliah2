package com.daberdev.learn.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ForexModel {
    @SerializedName("disclaimer")
    private String disclaimer;

    @SerializedName("license")
    private String license;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("base")
    private String base;

    @SerializedName("rates")
    private Map<String, Double> rates;

    // Getters
    public String getDisclaimer() { return disclaimer; }
    public String getLicense() { return license; }
    public long getTimestamp() { return timestamp; }
    public String getBase() { return base; }
    public Map<String, Double> getRates() { return rates; }
}
