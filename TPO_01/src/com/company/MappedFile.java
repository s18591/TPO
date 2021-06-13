package com.company;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class MappedFile {
    private static final Random RANDOM = new Random();
    private static final int size = 16;
    private RandomAccessFile _file;
    private MappedByteBuffer buffer;

    public MappedFile(File file, OperationMode mode) {
        try {
            if (mode == OperationMode.Write) {
                file.delete();
            } else {
                if (!file.exists() && !file.isFile() && !file.canRead()) {
                    throw new Exception("file" + file + "file cannot be read");
                }
            }
            _file = new RandomAccessFile(file, "rw");
            FileChannel channel = _file.getChannel();
            buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, size);
        } catch (Throwable ex) {
            ex.getStackTrace();
        }
    }

    public void write() {


        while (true) {
            buffer.rewind();
            buffer.getInt();
            int mark = buffer.getInt();
            if (mark == OperationMode.Read.getMark()) {
                int value1 = RANDOM.nextInt();
                int value2 = RANDOM.nextInt();
                buffer.rewind();
                buffer.putInt(OperationMode.Write.getMark()).putInt(value1).putInt(value2);
            }
        }
    }


    public void read() {
        main:
        while (true) {
            buffer.rewind();
            int mark = buffer.getInt();
            if (mark == OperationMode.Read.getMark()) {
                break main;
            } else {
                if (mark == OperationMode.Write.getMark()) {
                    int value1 = buffer.getInt();
                    int value2 = buffer.getInt();
                    buffer.putInt(OperationMode.Read.getMark());
                    int sum = value1 + value2;
                    System.out.println("sum: " + sum);
                }
            }
        }
    }

    public static void main(String args[]) {
        File file = new File("src/com/company/File.txt");
        Thread thread1 = new Thread();
        MappedFile mappedFile = new MappedFile(file, OperationMode.Write);
        mappedFile.write();
    }
}





