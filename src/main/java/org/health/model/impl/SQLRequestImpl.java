package org.health.model.impl;

import org.health.model.SQLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service("sqlRequest")
public class SQLRequestImpl implements SQLRequest {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String getTableCreationStatus() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS dogs");
            jdbcTemplate.execute("CREATE TABLE public.dogs\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    name character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    description character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    color_id integer,\n" +
                    "    CONSTRAINT dogs_pkey PRIMARY KEY (id)\n" +
                    ")");
            return "table created";
        } catch (Exception e) {
            return "table creation failed \n" + e.getMessage();
        }
    }

    @Override
    public Integer getInfo(String name) {
        String sql = "select count(*) from dogs where name = :name";
        SqlParameterSource namedparameter = new MapSqlParameterSource("name", name);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedparameter, Integer.class);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
