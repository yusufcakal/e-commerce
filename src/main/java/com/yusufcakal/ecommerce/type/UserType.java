package com.yusufcakal.ecommerce.type;

public enum UserType {

    ALREADY(1),
    NOTVERIFY(2);

    private int index;

    UserType(int index) {
        this.index = index;
    }

    public int index(){
        return index;
    }

}
