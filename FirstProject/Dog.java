package FirstProject;

public class Dog {
    private String name;
    private int age;
    private String breed;
    public Dog(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
    public Dog(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "Dog [name=" + name + ", age=" + age + ", breed=" + breed + "]";
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("Marcus Lepidus", 7, "Fox Terrier");
        System.out.println(dog1);
        Dog dog2 = dog1;
        System.out.println(dog2);
    }
}
