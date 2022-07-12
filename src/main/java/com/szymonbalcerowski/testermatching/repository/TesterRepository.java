package com.szymonbalcerowski.testermatching.repository;

import com.szymonbalcerowski.testermatching.model.Tester;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TesterRepository extends JpaRepository<Tester, Long> {


  @Query("SELECT country FROM Tester GROUP BY country")
  Set<String> findAllCountries();


  Page<Tester> findAll(Pageable page);

  Set<Tester> findDistinctByDevicesIdInAndCountryIn(Set<Long> devices, Set<String> countries);
}
