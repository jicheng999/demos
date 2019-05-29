package pers.ljc.interview.tanmer.utils;

import java.io.*;

/**
 * Created by liujicheng on 2019/5/28.
 */
public class FileReader {

    public static String getContentByNativeFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        byte[] contentByte = new byte[10240];
        int index = 0;
        int available = 0;
        while ((available = fileInputStream.available())>0) {
            fileInputStream.read(contentByte, index, available);
            index += available;
        }

        return new String(contentByte);
    }

    public static String getContentByNetPage(String pageUrl){

        return "";
    }
}
