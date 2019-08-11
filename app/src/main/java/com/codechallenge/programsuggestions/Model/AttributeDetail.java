package com.codechallenge.programsuggestions.Model;

public class AttributeDetail {
    public String code_name;
    public String value; //e.g. 2.0
    public String total; //e.g. 3.0

    public AttributeDetail (String value, String total) {
        this.value = value;
        this.total = total;
    }
}
