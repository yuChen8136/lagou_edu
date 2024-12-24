package com.lagou.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: username
 * @Date: 12/23/24 17:08
 * @Description:
 */
public enum StatusCode {
    SUCCESS(0, "保存成功"),
    FAIL(1,"保存失败！");
//    定义属性
    private int code;
    private String message;

    StatusCode() {
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("status", code);
        object.put("meg", message);
        return object.toString();
    }
}
