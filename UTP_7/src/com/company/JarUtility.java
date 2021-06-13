package com.company;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public final class JarUtility {
    public static List<String> searchByname(File file, final String name){
        if(!file.isFile()){
            return null;
        }
        try {
            JarFile jar = new JarFile(file);
            List<String> filenames = search(jar,name,e->searchByNamePredicate(e,name));
            jar.close();
            return filenames;
        }catch (Throwable e){
            e.printStackTrace();
        }

    return null;}
    public static List<String> searchByContent(File file,final  String content){
        if(!file.isDirectory()){
            return null;
        }
        try {
            JarFile jar = new JarFile(file);
            List<String> filenames= search(jar,content,e->searchByContentPredicate(e,jar,content));
            jar.close();
            return filenames;
        }catch (Throwable e){
            e.printStackTrace();
        return null;}
    }
    private static boolean searchByNamePredicate(JarEntry entry,final String name){
        return entry.getName().contains(name);
    }
    private static boolean searchByContentPredicate(final JarEntry entry,final JarFile jar,final String content){
        try{
            InputStream input = jar.getInputStream(entry);
            return FileContentUtility.contains(input,content,(int)entry.getSize());
        }catch (Throwable e){
            e.printStackTrace();
        }
    return false;}
    private static List<String> search(JarFile jar, final String name, Predicate<?super JarEntry> predicate){
        try {
            List<String> filenames = jar.stream().filter(predicate).map(JarEntry::getName).collect(Collectors.toList());
            jar.close();
            return filenames;
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;}
}
