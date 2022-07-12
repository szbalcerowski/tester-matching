package com.szymonbalcerowski.testermatching.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tester {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String firstName;
  private String lastName;
  private String country;
  private Timestamp lastLogin;
  @OneToMany(mappedBy = "tester", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Bug> bugs;
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "tester_device",
      joinColumns = { @JoinColumn(name = "tester_id") },
      inverseJoinColumns = { @JoinColumn(name = "device_id") }
  )
  private Set<Device> devices;
}
