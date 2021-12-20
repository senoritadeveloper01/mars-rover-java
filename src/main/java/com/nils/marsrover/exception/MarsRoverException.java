package com.nils.marsrover.exception;

import com.nils.marsrover.model.error.MarsRoverErrorCode;
import lombok.Getter;

/**
 * Handles project specific exceptions
 * @author  Nil Seri
 */
@Getter
public class MarsRoverException extends RuntimeException {

    private static final long serialVersionUID = -8111656859346000121L;

    private MarsRoverErrorCode errorCode;

    public MarsRoverException(MarsRoverErrorCode errorCode, Object... args) {
        this(errorCode, null, args);
    }

    public MarsRoverException(MarsRoverErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode.name() + " - " + String.format(errorCode.getMessage(), args), cause);
        this.errorCode = errorCode;
    }

}
