package com.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private Object data;
    private String message;

    public static Result success(Object data, String message){
        return new Result(true,data,message);
    }

    public static Result success(String message){
        return new Result(true,null,message);
    }
    public static Result success(Object data){
        return new Result(true,data,null);
    }

    public static Result fail(String message){
        return new Result(false,null,message);
    }
}
