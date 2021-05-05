package io.github.arumoy.covaxxer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndiaCovidVaccineSlotBookingApplication {

  public static void main(String[] args) {
    System.setProperty("java.awt.headless", "false");
    SpringApplication.run(IndiaCovidVaccineSlotBookingApplication.class, args);
  }
}
