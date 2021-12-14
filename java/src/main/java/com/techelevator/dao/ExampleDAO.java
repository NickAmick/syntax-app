package com.techelevator.dao;

import com.techelevator.model.Example;
import java.util.List;

public interface ExampleDAO {

    List<Example> getAllExamples();
    Example addExample(Example example, String username);
    boolean updateExample(Example example);
}
