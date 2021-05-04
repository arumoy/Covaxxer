package io.github.arumoy.covaxxer.beans.feignclients;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import io.github.arumoy.covaxxer.cowin.api.client.CowinAppointment;
import io.github.arumoy.covaxxer.cowin.api.client.CowinAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignSymptoms {
  @Value("${cowin.api.auth-base}")
  private String authBasePath;
  @Value("${cowin.api.appointment-base}")
  private String appointmentBasePath;

  @Bean
  CowinAuth auth() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new Slf4jLogger(CowinAuth.class))
        .logLevel(Logger.Level.FULL)
        .target(CowinAuth.class, authBasePath);
  }

  @Bean
  CowinAppointment appointment() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .logger(new Slf4jLogger(CowinAuth.class))
        .logLevel(Logger.Level.FULL)
        .target(CowinAppointment.class, appointmentBasePath);
  }
}
