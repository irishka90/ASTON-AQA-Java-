package org.aston.school.lesson1;

import java.util.Arrays;
//Реализовать сохранение данных в csv файл; Реализовать загрузку данных из csv файла. Файл читается целиком.
//
//Структура csv файла:
//Строка заголовок с набором столбцов
//Набор строк с целочисленными значениями
//Разделитель между столбцами - символ точка с запятой (;)
public class AppData {
    private String[] header;
    private int[][] data;

    public AppData(int x, int y) {
        this.header = new String[x];
        this.data = new int[y][x];
    }

    public void addHeader(int pos, String text) {
        header[pos] = text;
    }

    public String headerToCsv() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < header.length; i++) {
            builder.append(header[i]);
            if (i == header.length - 1) {
                builder.append("\n");
            } else {
                builder.append(";");
            }
        }
        return builder.toString();
    }

    public String dataToCsv() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                builder.append(data[i][j]);
                if (j != data[i].length - 1) {
                    builder.append(";");
                }
            }
            if (i != data.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public void addData(int posX, int posY, int z) {
        data[posX][posY] = z;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(Arrays.toString(header));
        for (int[] array : data) {
            builder.append("\n");
            builder.append(Arrays.toString(array));
        }
        return builder.toString();
    }
}
