package com.rio.proj.constant;

import lombok.Getter;

@Getter
public enum Position {
    ENGINEER(1,"engineer"),
    PRODUCER(2,"producer"),
    OTHER(3,"other");

    private int type;
    private String desc;
    Position(int type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public static int Match(String pos){
        if(pos.equals("engineer")){
            return 1;
        }else if(pos.equals("producer")){
            return 2;
        }else {
            return 3;
        }
    }
}
