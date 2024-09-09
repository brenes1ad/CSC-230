package FirstProject;
import java.util.Random;



public class FirstProjectApp {
    /**
     *
     * @param args -- command line argument
     */
    public static void main(String[] args) {
        System.out.println("Hello and Welcome");

        int i;
        for (i = 1; i < 6; i++)
            System.out.println("i= " + i + " and sqrt(i) = " + Math.sqrt(i));

        System.out.println("Int Max is  " + Integer.MAX_VALUE);
        System.out.println("rand = " + Math.random());
        System.out.println("rand2 = " + Math.random());
        System.out.println("rand3 = " + Math.random());

        Random rand = new Random();

        Dog fido = new Dog("Lucas",rand.nextInt(1,10), "Mutt");
        System.out.println("\n" + fido);
        
    }
}