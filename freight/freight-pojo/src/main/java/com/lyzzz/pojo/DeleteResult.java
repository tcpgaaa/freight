package com.lyzzz.pojo;

import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @location： freight\com.lyzzz.pojo
 * @creatTime: 2020/7/7  12:01
 * @author: Administrator
 * @remark:
 */
@ToString
public class DeleteResult implements Serializable {
    private int status;
    private String message;
    private List<?> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public DeleteResult(int status, String message, List<?> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public DeleteResult() {
    }
}
