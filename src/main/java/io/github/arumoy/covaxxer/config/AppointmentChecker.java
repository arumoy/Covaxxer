package io.github.arumoy.covaxxer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.arumoy.covaxxer.cowin.api.client.CowinAppointment;
import io.github.arumoy.covaxxer.dtos.VaxCenter;
import io.github.arumoy.covaxxer.dtos.VaxCenters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class AppointmentChecker {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentChecker.class);
  @Resource private TokenHolder holder;

  @Value("${cowin.date}")
  private String date;
  @Value("${cowin.district}")
  private Integer district;

  @Autowired private CowinAppointment appointment;

  @Autowired private ObjectMapper objectMapper;

  @Scheduled(fixedDelay = 10000)
  public void scheduleFixedDelayTask() {
    VaxCenters centers = appointment.cal(district, date + "-05-2021", holder.h());
    List<VaxCenter> vaxCenters =
        centers.getCenters().stream()
            .filter(
                f ->
                    f.getSessions().stream()
                        .anyMatch(m -> m.getMin_age_limit() == 18 && m.getAvailable_capacity() > 1))
            .collect(Collectors.toList());
    LOGGER.info("{}", vaxCenters);
  }
}
