package org.aston.school.lesson1;

public class Animal {

    private static int count;
    protected String name;
    protected int limitSwim;
    protected int limitRun;

    public Animal(String name) {
        count++;
        this.name = name;
    }

    public static int getCount() {
        return count;
    }

    public void run(int distance) {
        if (limitRun >= distance) {
            System.out.println(name + " пробежал " + distance + " метров");
        } else {
            System.out.println(name + " может пробежать только " + limitRun + " метров");
        }
    }

    public void swim(int distance) {
        if (limitSwim >= distance) {
            System.out.println(name + " проплыл " + distance + " метров");
        } else {
            System.out.println(name + " может проплыть только " + limitSwim + " метров");
        }
    }


}
