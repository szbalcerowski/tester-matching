package com.szymonbalcerowski.testermatching.mapper;

import com.szymonbalcerowski.testermatching.dto.DeviceDTO;
import com.szymonbalcerowski.testermatching.model.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

  DeviceDTO toDTO(Device device);
}
