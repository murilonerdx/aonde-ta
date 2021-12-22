package com.murilonerdx.aondeta.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldExceptionError {
    private String field;
    private String defaultMessage;
}
