package io.github.arumoy.covaxxer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class IndiaCovidVaccineSlotBookingApplication {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(IndiaCovidVaccineSlotBookingApplication.class);

  public static void main(String[] args) {
    System.setProperty("java.awt.headless", "false");
    SpringApplication.run(IndiaCovidVaccineSlotBookingApplication.class, args);
    LOGGER.info("=======================================================================");
  }
}
