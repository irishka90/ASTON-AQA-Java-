package org.aston.school.lesson1;

public class MainApp {
    public static void main(String[] args) {
        // Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
        // При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
        //Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
        // Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
        // или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
        // в какой именно ячейке лежат неверные данные.
        //В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException
        // и MyArrayDataException и вывести результат расчета.

        int x = 4;  //Изменить значение x или у для срабатывания ошибки
        int y = 4;
        String[][] table = new String[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                table[i][j] = "" + (i + j);

                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
      //   table[1][0] = "zas";  //Включить для срабатывания ошибки во второй части задания


        try {
            toDoInt(table);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }
    // Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
    // При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

    public static void mas4x4(String[][] table) throws MyArraySizeException {
        if (table.length != 4 || table[0].length != 4
                || table[1].length != 4 || table[2].length != 4
                || table[3].length != 4) {
            throw new MyArraySizeException();
        }
    }

    //Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
// должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
    public static void toDoInt(String[][] table) throws MyArrayDataException, MyArraySizeException {

        mas4x4(table);

        int z = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                try {
                    z += Integer.parseInt(table[i][j]); //добавление к сумме преобразованного в int значения ячейки
                } catch (NumberFormatException e) { // отлов ошибки преобразования стандартным Exception
                    throw new MyArrayDataException(i, j);  // Передача Exсeption в какой именно ячейке лежат неверные данные.
                }
            }
        }
        System.out.println(z);   // Вывод суммы всех преобразованных ячеек
    }

}
