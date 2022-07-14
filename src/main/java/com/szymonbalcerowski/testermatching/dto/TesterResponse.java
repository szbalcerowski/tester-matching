package com.szymonbalcerowski.testermatching.dto;

import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TesterResponse {

  private List<TesterWithExperienceDTO> testers;
  private long size;

}
