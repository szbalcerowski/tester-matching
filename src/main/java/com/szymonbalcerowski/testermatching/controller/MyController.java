package com.szymonbalcerowski.testermatching.controller;

import com.szymonbalcerowski.testermatching.dto.CountryDTO;
import com.szymonbalcerowski.testermatching.dto.DeviceDTO;
import com.szymonbalcerowski.testermatching.dto.TesterWithExperienceDTO;
import com.szymonbalcerowski.testermatching.service.DeviceService;
import com.szymonbalcerowski.testermatching.service.TesterMatchingService;
import com.szymonbalcerowski.testermatching.service.TesterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

  private final DeviceService deviceService;
  private final TesterService testerService;
  private final TesterMatchingService testerMatchingService;

  @GetMapping("/devices")
  public ResponseEntity<List<DeviceDTO>> getDevices() {
    return ResponseEntity.ok().body(deviceService.getAllDevices());
  }

  @GetMapping("/countries")
  public ResponseEntity<List<CountryDTO>> getCountries() {
    return ResponseEntity.ok().body(testerService.getAllCountries());
  }

  @GetMapping("/testers")
  public ResponseEntity<List<TesterWithExperienceDTO>> getTesters(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "5") int limit,
      @RequestParam(required = false) List<Long> devices,
      @RequestParam(required = false) List<String> countries

  ) {
    return ResponseEntity.ok().body(testerMatchingService.getTestes(page, limit, devices, countries));
  }

}
