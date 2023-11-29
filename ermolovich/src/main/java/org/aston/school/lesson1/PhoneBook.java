package org.aston.school.lesson1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    HashMap<Integer, String> phoneBook = new HashMap<>();

    public void add(Integer phone, String surname) {
        phoneBook.put(phone, surname);
    }

    public ArrayList<Integer> get(String surname) {
        if (phoneBook.containsValue(surname)) {
            ArrayList<Integer> phones = new ArrayList<>();
            for (Map.Entry<Integer, String> item : phoneBook.entrySet()) {
                if (item.getValue().equals(surname)) {
                    phones.add(item.getKey());
                }
            }
            return phones;
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "phoneBook=" + phoneBook;
    }
}
