package com.szymonbalcerowski.testermatching.service;

import com.szymonbalcerowski.testermatching.dto.DeviceDTO;
import com.szymonbalcerowski.testermatching.mapper.DeviceMapper;
import com.szymonbalcerowski.testermatching.repository.DeviceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceService {


  private final DeviceRepository deviceRepository;
  private final DeviceMapper deviceMapper;

  public List<DeviceDTO> getAllDevices(){
    return deviceRepository.findAll()
        .stream()
        .map(deviceMapper::toDTO)
        .collect(Collectors.toList());
  }
}
