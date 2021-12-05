package com.techelevator.dao;

import com.techelevator.model.Example;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcExampleDAOTest extends DAOIntegrationTest {

    private ExampleDAO exampleDAO;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        exampleDAO = new JdbcExampleDAO(jdbcTemplate);
    }

    @Test
    public void get_all_examples() {
        List<Example> expectedList = new ArrayList<>();
        String sql = "TRUNCATE TABLE examples";
        jdbcTemplate.update(sql);

        expectedList.add(insertExample("test example one", "test example one"));
        expectedList.add(insertExample("test example two", "test example two"));

        List<Example> testExampleList = exampleDAO.getAllExamples();

        Assert.assertEquals(expectedList, testExampleList);
    }

    private Example insertExample(String title, String snippet) {
        String sql = "INSERT INTO examples (example_id, title, snippet) VALUES (DEFAULT, ?, ?) RETURNING example_id";
        Integer exampleId = jdbcTemplate.queryForObject(sql, Integer.class, title, snippet);

        Example example = new Example();
        example.setExampleID(exampleId);
        example.setTitle(title);
        example.setSnippet(snippet);

        return example;
    }

}
