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
import java.awt.*;
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

  @Scheduled(fixedDelay = 60000)
  public void scheduleFixedDelayTask() {
    VaxCenters centers = appointment.cal(district, date + "-05-2021", holder.h());
    List<VaxCenter> vaxCenters =
        centers.getCenters().stream()
            .filter(
                f ->
                    f.getSessions().stream()
                        .anyMatch(m -> m.getMin_age_limit() == 18 && m.getAvailable_capacity() > 0))
            .collect(Collectors.toList());
    if (!vaxCenters.isEmpty()) {
      if (SystemTray.isSupported()) {
        StringBuilder builder = new StringBuilder();
        vaxCenters.forEach(center -> {
          builder.append(center.getName()).append('-').append(center.getPincode()).append(',');
        });
        try{
          showWinPopUp(builder.toString());
        } catch (AWTException e) {
          LOGGER.error(e.getMessage());
          LOGGER.info("Slot open at {}", builder);
        }
      }
    }
  }

  private void showWinPopUp(String centers) throws AWTException {
    //Obtain only one instance of the SystemTray object
    SystemTray tray = SystemTray.getSystemTray();

    //If the icon is a file
    Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
    //Alternative (if the icon is on the classpath):
    //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

    TrayIcon trayIcon = new TrayIcon(image, "Vaxx");
    //Let the system resize the image if needed
    trayIcon.setImageAutoSize(true);
    //Set tooltip text for the tray icon
    trayIcon.setToolTip("Book Vaccine Now");
    tray.add(trayIcon);

    trayIcon.displayMessage("Vaccine Notification", "Slot open @ " + centers, TrayIcon.MessageType.INFO);
  }
}
