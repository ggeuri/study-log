package com.ch.stompserver.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {
    private String homepageId;
    private String password;
}
