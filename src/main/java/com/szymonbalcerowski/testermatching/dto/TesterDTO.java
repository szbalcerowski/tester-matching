package com.szymonbalcerowski.testermatching.dto;

import com.szymonbalcerowski.testermatching.model.Bug;
import com.szymonbalcerowski.testermatching.model.Device;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TesterDTO {

  private int id;
  private String firstName;
  private String lastName;
  private String country;
  private Timestamp lastLogin;
//  private Set<Bug> bugs;
//  Set<Device> devices;
}
