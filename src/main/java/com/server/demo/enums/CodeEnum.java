package com.server.demo.enums;

public enum CodeEnum {
    INITIALIZE,
    GET_INFO(1),
    DO_SOMETHING(2);

    private int value;

    CodeEnum(int value) {
        this.value = value;
    }

    CodeEnum(){
    }

    public CodeEnum getValue(int value) {
        for(CodeEnum e: CodeEnum.values()) {
            if(e.value == value) {
                return e;
            }
        }
        return null;
    }
}
