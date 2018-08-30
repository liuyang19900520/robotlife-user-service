package com.liuyang19900520.robotlife.user.common.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Arrays;

/**
 * @author liuya
 */
public class LFileUtils {

    public static boolean isDirEmpty(String path) {
        File dir = new File(path);
        boolean directory = dir.isDirectory();
        if (directory) {
            long size = FileUtils.sizeOfDirectory(dir);
            if (0 == size) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static void splitFile(File srcFile, int eachSize) {
        // 判断文件是否符合拆分要求
        if (srcFile.length() == 0) {
            throw new RuntimeException("文件不符合拆分要求");
        }
        byte[] fileContent = new byte[(int) srcFile.length()];
        try {
            // 将文件内容读取到内存中
            FileInputStream fis = new FileInputStream(srcFile);
            fis.read(fileContent);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 计算要次要拆分为多少份
        int fileNumber;
        if (fileContent.length % eachSize == 0) {
            fileNumber = fileContent.length / eachSize;
        } else {
            fileNumber = fileContent.length / eachSize + 1;
        }

        for (int i = 0; i < fileNumber; i++) {
            String fileName = "split-" + srcFile.getName() + "-" + i + ".csv";
            // 在当前文件路径下创建拆分的文件
            File fi = new File(srcFile.getParent(), fileName);
            byte[] eachContent;

            // 将源文件内容复制到拆分的文件中
            if (i != fileNumber - 1) {
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, eachSize * (i + 1));
            } else {
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, fileContent.length);
            }

            try {
                FileOutputStream fos = new FileOutputStream(fi);
                fos.write(eachContent);
                fos.close();
                System.out.printf("输出子文件 %s,其大小是 %d,每个的大小是%d\n", fi.getAbsoluteFile(), fi.length(), eachContent.length);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }

    }

    /**
     * 按行分割文件
     *
     * @param rows                为多少行一个文件
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     */
    public static void splitDataToSaveFile(int rows, String sourceFilePath, String targetDirectoryPath) {
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return;
            }
        } else {
            targetFile.mkdirs();
        }
        try {

            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath), "UTF-8");
            BufferedReader br = new BufferedReader(in);

            BufferedWriter bw = null;
            String str = "";
            String tempData = br.readLine();
            int i = 1, s = 0;
            while (tempData != null) {
                str += tempData + "\r\n";
                if (i % rows == 0) {
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile.getAbsolutePath()
                            + "/" + "split-" + (s) + sourceFile.getName() + "_" + (s + 1) + ".csv"), "UTF-8"), 1024);

                    bw.write(str);
                    bw.close();

                    str = "";
                    s += 1;
                }
                i++;
                tempData = br.readLine();
            }
            if ((i - 1) % rows != 0) {

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile.getAbsolutePath() + "/"
                        + "split-" + (s) + sourceFile.getName() + "_" + (s + 1) + ".csv"), "UTF-8"), 1024);
                bw.write(str);
                bw.close();
                br.close();

                s += 1;
            }
            in.close();

        } catch (Exception e) {
        }
    }

}
