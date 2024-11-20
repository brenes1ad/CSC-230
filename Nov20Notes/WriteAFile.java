package Nov20Notes;

import java.io.*;

public class WriteAFile {

    public static void main(String[] args) {
        try{
            FileWriter fw = new FileWriter("foo.txt");
            fw.write("Hello World!");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("foo.txt");
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());

        File file1 = new File("PigBrenes");
        System.out.println(file1);
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.exists());
        System.out.println(file1.isFile());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());

        if (file1.isDirectory()){
            String[]dirList = file1.list();
            for(int i = 0; i < dirList.length; i++){
                System.out.println(dirList[i]);
            }
            for (File f: file1.listFiles()){
                System.out.println(f.getAbsolutePath());
            }
        }

        file.delete();

        File file5 = new File(file1, "Die.java");
        try{
            FileReader fis = new FileReader(file5);
            BufferedReader bis = new BufferedReader(fis);
            for (String line = bis.readLine(); line != null; line = bis.readLine()){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}
