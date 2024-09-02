package task2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("file2.txt");
        try (PrintStream ps = new PrintStream(path.toFile())) {
            Random random = new Random();
            int length = random.nextInt(10, 31);
            BufferedOutputStream bos = new BufferedOutputStream(ps);
            for(int i = 0; i < length; i++) {
                char c = random.nextBoolean() ?
                        (char) random.nextInt(65, 91)
                        :
                        (char) random.nextInt(97, 123);
                bos.write(c);
            }

            bos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return;
        }

        try{
            System.out.println(readFromFile(path));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static String readFromFile(Path path) throws IOException {
        try(FileReader fr = new FileReader(path.toFile())){
            BufferedReader br = new BufferedReader(fr);
            return br.readLine();
        }
    }
}
