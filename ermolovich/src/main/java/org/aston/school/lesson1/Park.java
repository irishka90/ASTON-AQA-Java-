package org.aston.school.lesson1;

import java.util.Arrays;

public class Park {

    private String name;
    public Attraction[] atractions;
    public Park (String name,Attraction[] atractions){
        this.name = name;
        this.atractions = atractions;
    }

    @Override
    public String toString() {
        return "Парк: "+name + " Аттракционы: "+ Arrays.toString(atractions);

    }

    public static class Attraction {
        private String name;
        private String time;
        private int price;
        public Attraction(String name, String time, int price){
            this.name = name;
            this.time= time;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Аттракцион: "+ name+ "Время работы: "+ time+ "Цена: "+price;
        }
    }
}
