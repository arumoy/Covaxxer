package io.github.arumoy.covaxxer.cowin.api.client;

import feign.RequestLine;
import io.github.arumoy.covaxxer.dtos.MobileNumberSubmission;
import io.github.arumoy.covaxxer.dtos.OTPSubmission;
import io.github.arumoy.covaxxer.dtos.TokenResponse;
import io.github.arumoy.covaxxer.dtos.TransactionIDResponse;

public interface CowinAuth {
  @RequestLine("POST /generateOTP")
  TransactionIDResponse otp(MobileNumberSubmission mobileNumber);

  @RequestLine("POST /confirmOTP")
  TokenResponse token(OTPSubmission otpSubmission);
}
