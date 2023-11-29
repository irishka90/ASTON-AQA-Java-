package org.aston.school.lesson1;

public class Cat extends Animal {

    private static int count = 0; //добавлен счетчик котов
    private static int plate = 50; // общая тарелка
    private boolean feedComplete = false;    //кот по-умолчанию голоден
    private int foodToEat;

    public Cat(String name, int foodToEat) {
        super(name);
        count++;
        this.limitRun = 200;           //по условию кот может бежать только 200м и не умеет плавать
        this.foodToEat = foodToEat;
    }

    public void eatFood() {
        if (plate >= foodToEat) {
            plate -= foodToEat;
            feedComplete = true;
        }

    }

    @Override
    public void swim(int distance) {
        System.out.println("Кот не умеет плавать");
    }

    @Override
    public String toString() {
        return name + ": " + (feedComplete ? "Сыт" : "Голоден");
    }


    public static int getCount() {
        return count;
    }

    public void addFood(int food){
        plate+=food;
    }
}
