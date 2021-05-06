package io.github.arumoy.covaxxer.cowin.api.client;

import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.arumoy.covaxxer.dtos.VaxCenters;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface CowinAppointment {
  @RequestLine("GET /calendarByDistrict?district_id={district_id}&date={date}")
  @Headers(
      "User-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
  VaxCenters cal(
      @Param("district_id") Integer districtId,
      @Param("date") String date,
      @HeaderMap Map<String, String> head);
}
