package com.szymonbalcerowski.testermatching.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.szymonbalcerowski.testermatching.repository.TesterRepository;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TesterServiceTest {

  @InjectMocks
  TesterService testerService;

  @Mock
  TesterRepository testerRepository;

  @Test
  public void testExample() {
    when(testerRepository.findAllCountries()).thenReturn(Set.of("DE", "PL"));

    var result = testerService.getAllCountries();

    assertEquals(2, result.size());
  }

}
