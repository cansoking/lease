package com.archie.lease.common.exception;

import com.archie.lease.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class LeaseException extends RuntimeException {
    Integer code;

    public LeaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum result) {
        super(result.getMessage());
        this.code = result.getCode();
    }
}
