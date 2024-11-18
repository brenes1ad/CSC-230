package Nov18Notes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Cat myCat = new Cat("Cheshire", 17);
        System.out.println(myCat);

        System.out.println("Let's save a cat to file cat.ser");
        try{
            FileOutputStream fos = new FileOutputStream("cat.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(LocalDateTime.now());
            oos.writeObject(myCat);
            oos.close();
        } catch (Exception e){
            System.out.println("Oops!" + e);
        }

        try{
            FileInputStream fis = new FileInputStream("cat.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            LocalDateTime time = (LocalDateTime) ois.readObject();
            Cat c = (Cat) ois.readObject();
            System.out.println("Cat's new identity is: " + c);
            System.out.println("stored at: " + time);
            System.out.println(myCat);
        } catch (Exception e){
            System.out.println("Oops!" + e);
        }

    }
}
