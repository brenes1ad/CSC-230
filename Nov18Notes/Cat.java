package Nov18Notes;

import java.io.Serializable;
import java.util.ArrayList;

public class Cat implements Serializable {

    private String name;
    private int age;
    private ArrayList<String> favoriteFoods;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.favoriteFoods = new ArrayList<>();
        favoriteFoods.add("Macaroni");
    }

    @Override
    public String toString() {
        return name + " " + age + " " + favoriteFoods;
    }
}
