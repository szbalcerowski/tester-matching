package com.szymonbalcerowski.testermatching.mapper;

import com.szymonbalcerowski.testermatching.dto.DeviceDTO;
import com.szymonbalcerowski.testermatching.dto.TesterDTO;
import com.szymonbalcerowski.testermatching.model.Device;
import com.szymonbalcerowski.testermatching.model.Tester;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TesterMapper {

  TesterDTO toDTO(Tester device);
}
