package io.github.arumoy.covaxxer.controllers;

import com.google.common.hash.Hashing;
import io.github.arumoy.covaxxer.config.TokenHolder;
import io.github.arumoy.covaxxer.cowin.api.client.CowinAuth;
import io.github.arumoy.covaxxer.dtos.MobileNumberSubmission;
import io.github.arumoy.covaxxer.dtos.OTPSubmission;
import io.github.arumoy.covaxxer.dtos.TokenResponse;
import io.github.arumoy.covaxxer.dtos.TransactionIDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;

@Controller
public class PageController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);
  @Autowired private CowinAuth auth;

  @Autowired private TokenHolder holder;

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("mobileNumber", new MobileNumberSubmission());
    return "home";
  }

  @PostMapping("/submit-otp")
  public String submitOTP(Model model, MobileNumberSubmission mobileNumberSubmission) {
    if (!holder.isEmpty()) {
      return "authenticated-message";
    }
    TransactionIDResponse idResponse = auth.otp(mobileNumberSubmission);
    OTPSubmission otpSubmission = new OTPSubmission();
    otpSubmission.setTxnId(idResponse.getTxnId());
    model.addAttribute("otpSubmission", otpSubmission);
    return "submit-otp";
  }

  @PostMapping("/authenticate")
  public String submitOTP(Model model, OTPSubmission otpSubmission) {
    String sha256hex = Hashing.sha256()
            .hashString(otpSubmission.getOtp(), StandardCharsets.UTF_8)
            .toString();
    LOGGER.info(sha256hex);
    LOGGER.info(otpSubmission.getTxnId());
    otpSubmission.setOtp(sha256hex);
    TokenResponse token = auth.token(otpSubmission);
    holder.update(token.getToken());
    LOGGER.info(token.getToken());
    return "authenticated-message";
  }
}
