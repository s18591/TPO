package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtility {
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date date(String format) throws ParseException{
        return FORMAT.parse(format);
    }

    public static Date deserialize(DataInputStream inputStream)throws IOException,ParseException{
        String s = inputStream.readUTF();
        return FORMAT.parse(s);
    }


    public static void serialize(Date date, DataOutputStream outputStream) throws IOException {
        String s = FORMAT.format(date);
        outputStream.writeUTF(s);
    }
}
