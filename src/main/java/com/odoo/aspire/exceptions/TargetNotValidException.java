package com.odoo.aspire.exceptions;

public class TargetNotValidException extends IllegalStateException {

    public TargetNotValidException(String target) {
        super(String.format("Target %s not supported. Use either local or grid", target));
    }

}
