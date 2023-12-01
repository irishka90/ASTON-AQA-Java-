package org.aston.school.lesson1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

class CsvUtil {

    public static void save(AppData appData) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(getPath()));
            out.write(appData.headerToCsv());
            out.write(appData.dataToCsv());
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static AppData read() {
        try {
            String text = new String(Files.readAllBytes(Paths.get(getPath())));
            String[] lines = text.split("\n");

            String[] headers = lines[0].split(";");
            AppData appDataRead = new AppData(headers.length, lines.length - 1);
            appDataRead.addHeaders(headers);

            for (int i = 1; i < lines.length; i++) {
                String[] lineData = lines[i].split(";");
                for (int j = 0; j < lineData.length; j++) {
                    appDataRead.addData(i - 1, j, Integer.parseInt(lineData[j]));
                }
            }

            return appDataRead;

        } catch (Exception e) {
            return null;
        }
    }

    private static String getPath() {
        return "demo.csv";
    }
}
