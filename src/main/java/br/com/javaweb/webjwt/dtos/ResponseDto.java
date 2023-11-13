package br.com.javaweb.webjwt.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class ResponseDto implements Serializable {
    private String status;
    private String message;
    private Object data;

    public static ResponseDto create() { return new ResponseDto();}
}
