package com.company;

import org.junit.Assert;
import org.junit.Test;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class PersonDatabaseTest {
    private final File file = new File("src/com/company/date.txt");
    private final  File biFile = new File("src/com/company/dataOut.dat");
    @Test
    public void create() throws IOException{
        PersonDatabase database = new PersonDatabase(file);
        try {
            Assert.assertEquals(2,database.bornOnDay(new SimpleDateFormat("yyyy-MM-DD").parse("1999-02-25")).size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(database.sortedByBirthdate().size(),10);
        Assert.assertEquals(database.sortedByFirstName().size(),10);
        Assert.assertEquals(database.sortedBySurnameFirstNameAndBirthdate().size(),10);

    }
    @Test
    public void serDes()throws Throwable{

        List<Person> people = InputParser.parse(file);
        PersonDatabase database = new PersonDatabase(people);

        OutputStream outputStream = new FileOutputStream(biFile);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        database.serialize(dataOutputStream);
        dataOutputStream.close();

        InputStream inputStream = new FileInputStream(biFile);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        PersonDatabase deserialized = PersonDatabase.deserialize(dataInputStream);
        dataInputStream.close();
        Assert.assertNotNull(deserialized);
        Assert.assertNotNull(deserialized);
    }
}
