package com.example.mvpdemo.model.entity;

/**
 * 身份证信息实体
 * Created by Administrator on 2017/4/2.
 */

public final class IDCardLoss {
    private final String cardno;
    private final String res;
    private final String tips;

    private IDCardLoss(Builder builder) {
        cardno = builder.cardno;
        res = builder.res;
        tips = builder.tips;
    }

    public static final class Builder {
        private String cardno;
        private String res;
        private String tips;

        public Builder cardno(String cardno){
            this.cardno=cardno;
            return this;
        }
        public Builder res(String res){
            this.res=res;
            return this;
        }
        public Builder tips(String tips){
            this.tips=tips;
            return this;
        }

        public IDCardLoss build() {
            return new IDCardLoss(this);
        }

    }

}
