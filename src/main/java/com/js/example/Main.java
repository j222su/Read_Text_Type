package com.js.example;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

        byte[] buf = new byte[4096];
        UniversalDetector detector = null;

        try {
            File file = new File("C:\\text_sample\\privacy_sample.txt");

            FileInputStream fis = new FileInputStream(file);

            detector = new UniversalDetector(null);

            int nread;

            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }

            detector.dataEnd();

            String encoding = detector.getDetectedCharset();
            if (encoding != null) {
                System.out.println("Detected encoding = " + encoding);
            } else {
                System.out.println("No encoding detected.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // (5)
        detector.reset();

    }
}
