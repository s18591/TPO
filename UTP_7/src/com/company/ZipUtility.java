package com.company;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipUtility {
    public static List<String> searchByName(File file,final String name){
        if(!file.isFile()){
            return null;
        }
        try {
            ZipFile zip = new ZipFile(file);
            List<String> fileNames = zip.stream().filter(e -> searchByNamePredicate(e,name)).map(ZipEntry::getName).collect(Collectors.toList());
            zip.close();
            return fileNames;
        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
    }
    public static List<String> searchByContent(File file,final String content){
        if(!file.isDirectory()){
            return null;
        }
        try {
            ZipFile zip = new ZipFile(file);
            List<String> filenames = zip.stream().filter(e->searchByContentPredicate(e,zip,content)).map(e->((ZipEntry) e).getName()).collect(Collectors.toList());
            zip.close();
            return filenames;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private static boolean searchByNamePredicate(ZipEntry entry,String name){
        return entry.getName().contains(name);
    }
    private static boolean searchByContentPredicate(ZipEntry entry,ZipFile zip,String content){
        try {
            InputStream input = zip.getInputStream(entry);
            return FileContentUtility.contains(input,content,(int)entry.getSize());
        }catch (Throwable e){
            e.printStackTrace();
        }
        return false;
    }
}
