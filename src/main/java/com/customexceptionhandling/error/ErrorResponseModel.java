package com.customexceptionhandling.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseModel {
    private int statusCode;
    private String date;
    private String restErrorMessage;
    private String detailedErrorMessage;
}
