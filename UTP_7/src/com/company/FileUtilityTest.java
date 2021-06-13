package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public final class FileUtilityTest {
    private final File data = new File("src/com/company/Data");
    private final File file = new File("src/com/company/Data/file.txt");
    private final File zipDate = new File("src/com/company/zipData.zip");
    private final File zipFile = new File("src/com/company/zipData/zipFile.txt");


    @Test
    public void fileUtilityTest(){
        List<File> files = FileUtility.searchByname(data,"Data");
        Assert.assertTrue(data.exists());
        Assert.assertTrue(file.exists());
        Assert.assertEquals(1,files.size());
        File file = files.get(0);
        Assert.assertEquals("file.txt",file.getName());
        Assert.assertEquals(file,file);
    }
    @Test
    public void zipUtilityTest(){
        List<File> files = FileUtility.searchByContent(data,"Aizen");
        List<String> files1 = ZipUtility.searchByName(zipDate,"zipDate");
        Assert.assertEquals("zipFile.txt",zipFile.getName());
        Assert.assertEquals(1,files.size());
        File file = files.get(0);
        Assert.assertEquals("file.txt",file.getName());
        Assert.assertEquals(file,file);
    }
}
