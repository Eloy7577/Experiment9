package com.example.shenchen.experiment9;

/**
 * Created by shenchen on 2019/5/26.
 */

public class Contract {
    private  String name;
    private  String phone;

    public Contract(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
