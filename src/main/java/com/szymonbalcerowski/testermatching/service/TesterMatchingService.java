package com.szymonbalcerowski.testermatching.service;

import com.szymonbalcerowski.testermatching.dto.TesterWithExperienceDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TesterMatchingService {

  private final TesterService testerService;


  public List<TesterWithExperienceDTO> getTestes(int page, int limit, List<Long> devices, List<String> countries) {
    List<TesterWithExperienceDTO> result = testerService.getTestes(page, limit, devices, countries);
    long size = testerService.count(devices, countries);

    return result;
  }
}
