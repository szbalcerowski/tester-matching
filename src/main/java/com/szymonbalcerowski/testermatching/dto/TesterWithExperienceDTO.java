package com.szymonbalcerowski.testermatching.dto;

import java.sql.Timestamp;
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
public class TesterWithExperienceDTO {

  private long experience;
  private long id;
  private String firstName;
  private String lastName;
  private String country;
  private Timestamp lastLogin;

}
