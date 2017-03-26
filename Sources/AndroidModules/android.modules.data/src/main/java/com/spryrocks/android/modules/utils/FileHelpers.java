package com.spryrocks.android.modules.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelpers {
    public static void write(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        int read;
        while ((read = inputStream.read(buffer, 0, bufferSize)) > 0) {
            outputStream.write(buffer, 0, read);
        }
        outputStream.flush();
    }

    public static void write(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            write(inputStream, fileOutputStream, 1024 * 10);
        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
        }
    }

    public static void write(InputStream inputStream, String fileName) throws IOException {
        write(inputStream, new File(fileName));
    }
}