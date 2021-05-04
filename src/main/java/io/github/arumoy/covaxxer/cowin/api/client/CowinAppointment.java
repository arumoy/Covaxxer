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
  VaxCenters cal(@Param("district_id") Integer districtId, @Param("date") String date, @HeaderMap Map<String, String> head);
}
