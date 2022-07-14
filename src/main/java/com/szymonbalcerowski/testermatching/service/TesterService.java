package com.szymonbalcerowski.testermatching.service;

import static java.util.Objects.nonNull;
import com.szymonbalcerowski.testermatching.dto.CountryDTO;
import com.szymonbalcerowski.testermatching.dto.TesterWithExperienceDTO;
import com.szymonbalcerowski.testermatching.repository.TesterRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TesterService {

// TODO elaborate during interview
//  SELECT t.*, 0 as experianced
//  FROM tester t
//  LEFT JOIN tester_device td on t.id = td.tester_id
//  LEFT JOIN bug b on t.id = b.tester_id and b.device_id = td.device_id
//  WHERE td.device_id in (1,3) and b.id is null
//  group by t.id

  private final TesterRepository testerRepository;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<CountryDTO> getAllCountries() {
    return testerRepository.findAllCountries().stream().map(CountryDTO::new).collect(Collectors.toList());
  }

  public List<TesterWithExperienceDTO> getTestes(int page, int limit, List<Long> devices, List<String> countries) {
    PageRequest pageable = PageRequest.of(page, limit);
    QueryHelper queryHelper = getParamsAndQuery(devices, countries, "SELECT t.*, count(b) as experience ");

    MapSqlParameterSource params = queryHelper.getParams();
    params.addValue("offset", pageable.getOffset());
    params.addValue("limit", limit);

    String query = queryHelper.getQuery()
      .append("group by t.id ")
      .append("order by experience desc ")
      .append("LIMIT :limit OFFSET :offset ")
      .toString();

    return namedParameterJdbcTemplate.query(query, params, new TestWithExperienceRowMapper());
  }

  public QueryHelper getParamsAndQuery(List<Long> devices, List<String> countries, String select) {
    MapSqlParameterSource params = new MapSqlParameterSource();

    StringBuilder query = new StringBuilder()
      .append(select)
      .append("FROM tester t ");
    if (nonNull(devices)) {
      query.append("LEFT JOIN tester_device td on t.id = td.tester_id ");
    }
    query.append("LEFT JOIN bug b on t.id = b.tester_id ");

    if (nonNull(devices)) {
      query.append("and b.device_id = td.device_id ");
    }

    query.append("WHERE ");

    Optional.ofNullable(countries).ifPresent(c -> {
      params.addValue("countries", countries);
      query.append("t.country in (:countries) AND ");
    });
    Optional.ofNullable(devices).ifPresent(c -> {
      params.addValue("devices$", devices);
      query.append("b.device_id in (:devices$) or b.id is null AND ");
    });
    query.append("1=1 ");
    return new QueryHelper(query, params);
  }


  public long count(List<Long> devices, List<String> countries) {
    QueryHelper queryHelper = getParamsAndQuery(devices, countries, "SELECT count(distinct t.id)");
    return namedParameterJdbcTemplate.queryForObject(queryHelper.getQuery().toString(), queryHelper.getParams(), Long.class);

  }


  private static class TestWithExperienceRowMapper implements RowMapper<TesterWithExperienceDTO> {

    @Override
    public TesterWithExperienceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      return TesterWithExperienceDTO.builder()
        .id(rs.getLong("id"))
        .country(rs.getString("country"))
        .lastLogin(rs.getTimestamp("last_login"))
        .firstName(rs.getString("first_name"))
        .lastName(rs.getString("last_name"))
        .experience(rs.getLong("experience"))
        .build();
    }
  }

  @AllArgsConstructor
  @Getter
  private class QueryHelper {

    private StringBuilder query;
    private MapSqlParameterSource params;
  }
}
