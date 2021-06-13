package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data
    //    U¿yæ regularnych wyra¿eñ (Pattern) do walidacji danych wejœciowych
    //
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
    //    Konwersjê wejœciowego ci¹gu znaków reprezentuj¹cego datê nale¿y oprzeæ np. DateFormat
    //    SimpleDateFormat format "yyyy-MM-dd"

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-DD");
    private static final String FIRSTNAMEG = "firstname";
    private static final String SURNAMEG = "surname";
    private static final String BIRTHDATEG = "birthdate";
    private static final String NAMEG = "(?:[A-Z]([a-z])+)";
    private static final String FIRSTNAMEP = "(?<" + FIRSTNAMEG + ">" + NAMEG + ")";
    private static final String SURNAMEP = "(?<" + SURNAMEG + ">" + NAMEG + ")";
    private static final String YEARP = "(?:[0-9]{4})";

    private static final String MONTHP = "(?:(?:[0][1-9])|(?:[1][0-2]))";
    private static final String DAYP = "(?:(?:[0][1-9])|(?:[1-2][0-9])|(?:[3][0-1]))";
    private static final String SEPERATOR = ".";
    private static final String BIRTHDATEP = "(?<" + BIRTHDATEG + ">" + YEARP + SEPERATOR + MONTHP + SEPERATOR + DAYP + ")";
    private static final String SPACE = "(?:[ \t]+)";
    private static final String LINE = FIRSTNAMEP + SPACE + SURNAMEP + SPACE + BIRTHDATEP;
    private static final Pattern LINE_GROUP = Pattern.compile(LINE);




    public static List<Person> parse(File file) throws IOException {
        BufferedReader reader = null;
        List<Person> people = new ArrayList<>();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String line;
        while ((line = reader.readLine()) != null) {
            Person person = parse(line);
            if (person != null) {
                people.add(person);
            }
        }
        reader.close();
        return people;
    }

    private static Person parse(String line) {
        Matcher matcher = LINE_GROUP.matcher(line);
        if (!matcher.matches()) {
            System.out.println(1);
            return null;
        }
        String firstName = matcher.group(FIRSTNAMEG);
        String surname = matcher.group(SURNAMEG);
        Date birthdate = null;
        try {
            birthdate = FORMAT.parse(matcher.group(BIRTHDATEG));
        } catch (ParseException e) {
            System.out.println(2);
        }
        return new Person(firstName,surname,birthdate);
    }

}