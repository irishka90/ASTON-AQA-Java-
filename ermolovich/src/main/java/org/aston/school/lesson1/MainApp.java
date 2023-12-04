package org.aston.school.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

public class MainApp {


    public static void main(String[] args) {
        System.out.println("Количество четных:" + countEvenNumbers());// 1.
        collectionHigh();  //2.
    }

    private static int countEvenNumbers() {
        //1. Для любого набора случайно-сгенерированных чисел нужно определить количество чётных чисел.

        Predicate<Integer> countEvenFunc = (n) -> n % 2 == 0;

        Random random = new Random();    // Создаю массив случайных чисел, длинна массива до 12 штук
        int[] nums = new int[random.nextInt(12)];

        int counterEven = 0;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt();
            if (countEvenFunc.test(nums[i])) {
                counterEven++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return counterEven;

    }

    //2. Задана коллекция, состоящая из строк: «Highload», «High», «Load», «Highload». Нужно с ней выполнить следующие манипуляции:
    //2.1. Посчитать, сколько раз объект «High» встречается в коллекции;
    //2.2. Определить, какой элемент в коллекции находится на первом месте. Если мы получили пустую коллекцию, то пусть возвращается 0;
    //2.3. Необходимо вернуть последний элемент, если получили пустую коллекцию, то пусть возвращается 0;
    private static void collectionHigh() {

        List<String> high = Arrays.asList("Highload", "High", "Load", "LoadHigh"); //Коллекция исходных значений
        Random random = new Random();

        Function<Integer, List<String>> listGenerator = collectionSize -> {

            ArrayList<String> newHigh = new ArrayList<>(); //Создание коллекции из случайной комбинации из исходных значений

            for (int i = 0; i < collectionSize; i++) {
                int position = random.nextInt(high.size()); //выбираем случайную позицию в исходной коллекции
                String word = high.get(position); // получаем значение по позиции
                newHigh.add(word);  //добавляем его в новую коллекцию
            }
            return newHigh;
        };

        int collectionSize = random.nextInt(20); //задаем случайный размер (до 20 слов)
        List<String> newHigh = listGenerator.apply(collectionSize);
        System.out.println(newHigh); //новая коллекция

        //2.1
        Function<List<String>, Integer> action1 = highWords -> {
            int counter = 0;  //подсчет вхождений High в новой коллекции
            for (String word : highWords) {
                if (word.contains("High")) {
                    counter++;
                }
            }
            return counter;
        };

        System.out.println("High кол-во:" + action1.apply(newHigh));

        //2.2-2.3
        HighWord action2 = (highWords, firstOrLast) -> {
            if (highWords.isEmpty()) return "0";

            if (firstOrLast) return highWords.get(0);

            return highWords.get(highWords.size() - 1);
        };

        System.out.println("первое слово: " + action2.getItem(newHigh, true));
        System.out.println("последнее слово: " + action2.getItem(newHigh, false));
    }


}
