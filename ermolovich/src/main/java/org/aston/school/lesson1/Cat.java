package org.aston.school.lesson1;

public class Cat extends Animal {

    private boolean feedComplete = false;    //кот по-умолчанию голоден
    private int foodToEat;

    public Cat(String name, int foodToEat) {
        super(name);
        this.limitRun = 200;           //по условию кот может бежать только 200м и не умеет плавать
        this.foodToEat = foodToEat;
    }

    @Override
    public void swim(int distance) {
        System.out.println("Кот не умеет плавать");
    }

    public void setFeedComplete(boolean feedComplete) {
        this.feedComplete = feedComplete;
    }

    public int getFoodToEat() {
        return foodToEat;
    }

    @Override
    public String toString() {
        return name + ": " + (feedComplete ? "Сыт" : "Голоден");
    }
}
