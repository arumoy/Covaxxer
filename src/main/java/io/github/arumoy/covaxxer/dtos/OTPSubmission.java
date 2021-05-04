package io.github.arumoy.covaxxer.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPSubmission {
    private String otp;
    private String txnId;
}
