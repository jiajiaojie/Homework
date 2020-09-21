package com.example.io;

// 用 Okio 从文本文件中读取数据，并上传代码截图。
// 用 Okio 从复制一份文件，并上传代码截图。

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class IOHomeWork {

    public static void main(String[] args) {
//        readTest();
//        copyFile();
//        okReadTest();
        okCopyFile();
    }

    /**
     * 用 Java I/O 的 API 从文本文件中读取数据
     */
    private static void readTest() {
        try (FileInputStream fileInputStream = new FileInputStream(new File("./io/text.txt"));
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String test;
            do {
                test = bufferedReader.readLine();
                if (test != null) {
                    System.out.println(test);
                }
            } while (test != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 Java I/O 的 API 复制一个文件
     */
    private static void copyFile() {
        try (FileInputStream fileInputStream = new FileInputStream(new File("./io/text.txt"));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(new File("./io/text2.txt"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, count);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用 Okio 从文本文件中读取数据
     */
    private static void okReadTest() {
        try (BufferedSource bufferedSource = Okio.buffer(Okio.source(new File("./io/text.txt")))) {
            String line;
            do {
                line = bufferedSource.readUtf8Line();
                if (line != null) {
                    System.out.println(line);
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用 Okio 从复制一份文件
     */
    private static void okCopyFile() {
        try (Source source = Okio.source(new File("./io/text.txt"));
             Sink sink = Okio.sink(new File("./io/text3.txt"))) {
            Buffer buffer = new Buffer();
            long count;
            while((count = source.read(buffer, 1024)) != -1) {
                sink.write(buffer, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
