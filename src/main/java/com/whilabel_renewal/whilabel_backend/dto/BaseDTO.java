package com.whilabel_renewal.whilabel_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseDTO<T> {

    private String message;

    private T data;


}
