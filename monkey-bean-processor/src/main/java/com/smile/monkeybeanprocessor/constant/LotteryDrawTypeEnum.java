package com.smile.monkeybeanprocessor.constant;

/**
 * @ClassName LotteryDrawTypeEnum
 * @Author yamei
 * @Date 2020/8/17
 **/
public enum LotteryDrawTypeEnum {
    ANNUALMETTING("annual-metting") {
        @Override
        public void probability() {
            System.out.println("metting");
        }
    },
    DINNER("dinner") {
        @Override
        public void probability() {
            System.out.println("dinner");
        }
    };

    private String typeName;

    private LotteryDrawTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public abstract void probability();

    public static LotteryDrawTypeEnum getEnum(String typeName) {
        for (LotteryDrawTypeEnum v : values()) {
            if (v.getTypeName().equals(typeName)) {
                return v;
            }
        }
        throw new IllegalArgumentException("error typeName :" + typeName);
    }
}
