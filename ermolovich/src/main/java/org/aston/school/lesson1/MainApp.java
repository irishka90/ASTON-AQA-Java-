package org.aston.school.lesson1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {


    public static void main(String[] args) {
        System.out.println("Количество четных:" + countEvenNumbers());// 1.
        collectionHigh();  //2.
        sortUp();   //3.
        students(); //4
        logins(); //5
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


    private static List<String> generateList(List<String> original) { //original - Коллекция исходных значений
        Random random = new Random();

        Function<Integer, List<String>> listGenerator = collectionSize -> {

            ArrayList<String> newHigh = new ArrayList<>(); //Создание коллекции из случайной комбинации из исходных значений

            for (int i = 0; i < collectionSize; i++) {
                int position = random.nextInt(original.size()); //выбираем случайную позицию в исходной коллекции
                String word = original.get(position); // получаем значение по позиции
                newHigh.add(word);  //добавляем его в новую коллекцию
            }
            return newHigh;
        };

        int collectionSize = random.nextInt(20); //задаем случайный размер (до 20 слов)
        return listGenerator.apply(collectionSize);
    }

    //2. Задана коллекция, состоящая из строк: «Highload», «High», «Load», «Highload». Нужно с ней выполнить следующие манипуляции:
    //2.1. Посчитать, сколько раз объект «High» встречается в коллекции;
    //2.2. Определить, какой элемент в коллекции находится на первом месте. Если мы получили пустую коллекцию, то пусть возвращается 0;
    //2.3. Необходимо вернуть последний элемент, если получили пустую коллекцию, то пусть возвращается 0;
    private static void collectionHigh() {

        List<String> newHigh = generateList(Arrays.asList("Highload", "High", "Load", "LoadHigh"));
        System.out.println(newHigh); //новая коллекция

        //2.1
        Function<List<String>, Long> action1 = highWords -> highWords.stream()
                .filter(word -> word.contains("High"))
                .count();

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


    //3. Задана коллекция, содержащая элементы "f10", "f15", "f2", "f4", "f4".
    // Необходимо отсортировать строки по возрастанию и добавить их в массив;
    private static void sortUp() {
        List<String> newNotSorted = generateList(Arrays.asList("f10", "f15", "f1", "f42", "f7", "f4"));
        System.out.println(newNotSorted); //новая коллекция

        newNotSorted.sort((o1, o2) -> {
            int i1 = Integer.parseInt(o1.replace("f", ""));
            int i2 = Integer.parseInt(o2.replace("f", ""));
            return i1 - i2;
        });

        System.out.println(newNotSorted); //отсортированная коллекция
    }

    /*
 4. Создай класс со следующим содержимым: (СМ. СКРИНШОТ)
4.1. Необходимо узнать средний возраст студентов мужского пола;
4.2. Кому из студентов грозит получение повестки в этом году при условии,
 что призывной возраст установлен в диапазоне от 18 до 27 лет;
     */
    private static void students() {
        Collection<Student> students = Arrays.asList(
                new Student("Дмитрий", 17, Gender.MAN),
                new Student("Максим", 20, Gender.MAN),
                new Student("Екатерина", 20, Gender.WOMAN),
                new Student("Михаил", 28, Gender.MAN)
        );
        //4.1
        Stream<Integer> ages = students.stream()   //Преобразуем коллекцию в поток
                .filter(student -> student.getGender() == Gender.MAN) // отфильтровываем только мужчин
                .map(student -> student.getAge());        //Преобразовали поток студентов в поток, содержащий только их возраст

        List<Integer> arrAges = ages.collect(Collectors.toList());  //Преобразовали поток в коллекцию

        int summ = arrAges.stream() //Cоздаем поток возрастов
                .reduce(0, Integer::sum); // Считаем сумму всех элементов потока

        double averageAge = (double) summ / arrAges.size();  //Считаем средний возраст
        System.out.println("Средний возраст мужчинок:" + averageAge);

        //4.2
        List<Student> writs = students.stream()
                .filter(student -> student.getGender() == Gender.MAN && student.getAge() <= 27 && student.getAge() >= 18)
                .collect(Collectors.toList());

        System.out.println(writs);
    }

    //5. Нужно написать программу, которая будет принимать от пользователя ввод различных логинов.
    // Как только пользователь введет пустую строку - программа должна прекратить приём данных от
    // пользователя и вывести в консоль логины, начинающиеся на букву f (строчную).

    private static void logins() {
        ArrayList<String> logins = new ArrayList<>();
        String x = "";

        Scanner in = new Scanner(System.in);
        while (!(x = in.nextLine()).isEmpty()) {
            logins.add(x);
        }

        List<String> loginsF = logins.stream()
                .filter(w -> w.startsWith("f"))
                .collect(Collectors.toList());
        System.out.println(loginsF);

    }

}
