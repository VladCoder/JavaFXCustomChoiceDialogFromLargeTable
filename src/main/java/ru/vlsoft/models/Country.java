package ru.vlsoft.models;

public class Country extends BaseObject {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return getName();
    }
}
