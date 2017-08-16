package org.njusc.exception;

import org.njusc.dto.SeckillExecution;

/**
 * Created by Echo on 17/8/15.
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
