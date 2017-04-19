package com.example.mvpdemo.model.entity;

/**
 * 身份证信息实体
 * Created by Administrator on 2017/4/2.
 */

public final class IDCard {
    private final String sex;
    private final String area;
    private final String birthday;

    private IDCard(Builder builder) {
        this.sex = builder.sex;
        this.area = builder.area;
        this.birthday = builder.birthday;
    }

    public String getSex() {
        return sex;
    }

    public String getArea() {
        return area;
    }

    public String getBirthday() {
        return birthday;
    }

    public static class Builder {
        private String sex;
        private String area;
        private String birthday;
        public Builder sex(String sex){
            this.sex = sex;
            return this;
        }
        public Builder area(String area){
            this.area = area;
            return this;
        }
        public Builder birthday(String birthday){
            this.birthday = birthday;
            return this;
        }

        public IDCard build() {
            return new IDCard(this);
        }
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
