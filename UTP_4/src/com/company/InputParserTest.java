package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InputParserTest {
    private static final File file = new File("src/com/company/date.txt");

    @Test
    public void parse() throws IOException{
        Assert.assertTrue(file.exists());
        Assert.assertEquals(274,file.length());
        List<Person> people = InputParser.parse(file);
        Assert.assertNotNull(people);
        Assert.assertEquals(10,people.size());
    }
}
