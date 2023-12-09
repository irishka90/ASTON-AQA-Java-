package org.aston.school.lesson1;

import java.util.*;

public class MainApp {
    //Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    // Найти и вывести список уникальных слов, из которых состоит массив
    // (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово. (реализовать с использованием коллекций)
    public static void main(String[] args) {

        List<String> animals = Arrays.asList("cat", "dog", "rabbit", "cat", "fish", "parrot",
                "hamster", "cat", "dog", "parrot", "pig", "cat", "dog", "pig", "fish", "rabbit", "fish", "rat");


        System.out.println(animals);

        HashMap<String, Integer> animalsMap = new HashMap<>();
        for (String animal : animals) {

            if (!animalsMap.containsKey(animal)) {
                animalsMap.put(animal, 1);
            } else {
                animalsMap.put(animal, animalsMap.get(animal) + 1);
            }
        }
        System.out.println(animalsMap);


        System.out.println(animalsMap.keySet()); //Вывод списка животных без дубликата
        /*
       // Вывод значений из списка, которые встречаются только 1раз
        ArrayList<String> uniqueAnimals = new ArrayList<>();
        for (Map.Entry<String, Integer> item : animalsMap.entrySet()) {
            if (item.getValue() == 1) {
                uniqueAnimals.add(item.getKey());
            }
        }

        System.out.println(uniqueAnimals);
         */


        /*
        Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
         В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер
          телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
          тогда при запросе такой фамилии должны выводиться все телефоны.
         */
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(971234567, "Ivanov");
        phoneBook.add(712346767, "Ivanov");
        phoneBook.add(512135967, "Petrov");
        phoneBook.add(267298767, "Sidorov");
        phoneBook.add(123098767, "Sidorov");
        phoneBook.add(57232877, "Kotov");
        phoneBook.add(354634567, "Ivanov");


        System.out.println(phoneBook);

        System.out.println(phoneBook.get("Petrov"));
    }



}
