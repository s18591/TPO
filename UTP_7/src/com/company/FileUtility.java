package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public final class FileUtility {
    private static final int PathLength = 255;

    public static List<File> searchByname(File directory,final String name){
        try{
            List<File> files = search(directory,name,FileUtility::searchBynamePredicate);
            return files;
        }catch (Throwable e){
            e.printStackTrace();
        }
    return null;
    }

    public static List<File> searchByContent(File directory, final  String content){
        try{
            List<File> files = search(directory,content,FileUtility::searchByContentPredicate);
            return files;
        }catch (Throwable e){
           e.printStackTrace();
        }
    return null; }
    private static List<File> search(File directory, String searchParameter, BiPredicate<Path,String> predicate){
        if(!directory.isDirectory()){
            return null;
        }
        try {
            Path path = directory.toPath();
            List<File> files = Files.find(path,PathLength,(p, attributes) -> predicate.test(path,searchParameter))
                    .map(p -> p.toFile())
                    .filter(File::isFile)
                    .collect(Collectors.toList());
            return files;
        }catch (Throwable e){
            e.printStackTrace();
        }
    return null;}
    private static boolean searchBynamePredicate(Path path,final String name){
        File file = path.toFile();
        if(file.isDirectory()){
            return true;
        }
        String fileName = file.getName();
        return fileName.contains(name);
    }
    private static boolean searchByContentPredicate(Path path,final  String content){
        try{
            File file = path.toFile();
            if(file.isDirectory()){
                return true;
            }
            InputStream input = new FileInputStream(path.toFile());
            return FileContentUtility.contains(input,content,(int)file.length());
        }catch (Throwable e){
            e.printStackTrace();
        }
    return false;}

}
