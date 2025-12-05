package com.ch.model1.dto;

import lombok.Data;

// response 안쪽: header + body
@Data
public class Response {
    private Header header;
    private Body body;
}