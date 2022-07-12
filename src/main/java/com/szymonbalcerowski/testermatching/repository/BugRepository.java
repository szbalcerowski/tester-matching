package com.szymonbalcerowski.testermatching.repository;

import com.szymonbalcerowski.testermatching.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

}
