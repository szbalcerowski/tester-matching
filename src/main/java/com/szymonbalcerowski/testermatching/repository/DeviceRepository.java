package com.szymonbalcerowski.testermatching.repository;

import com.szymonbalcerowski.testermatching.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
