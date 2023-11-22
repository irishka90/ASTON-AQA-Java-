package org.aston.school.lesson1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MainApp {
    public static void main(String[] args) {

        int y = 3;
        int x = 5;
        AppData appData = new AppData(x, y);

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (i == 0) {
                    appData.addHeader(j, "Val " + (j + 1));
                }
                appData.addData(i, j, (i + j) * 1000);
            }
        }


        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("demo.csv"));
            out.write(appData.headerToCsv());
            out.write(appData.dataToCsv());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader out = new BufferedReader(new FileReader("demo.csv"));

            String str;
            while ((str = out.readLine()) != null) {
                System.out.println(str);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(appData);
    }
    }



