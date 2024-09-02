package task1;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter filename or path to the file:");
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get(scanner.nextLine());

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace(System.out);
                return;
            }
        }

        LocalDateTime now = LocalDateTime.now();
        String text;
        String date = "[" +  now.getHour() + ":" + now.getMinute() + " " + now.toLocalDate() + "]";
        Charset charset = Charset.forName("cp1251");
        try {
            List<String> lines = Files.readAllLines(path);
            Serializable _ = lines.isEmpty() ? lines.add(date) : lines.set(0, date);
            Files.write(path, lines, charset);

            while (!Objects.equals(text = scanner.nextLine(), "/exit")) {
                if(Objects.equals(text, "/r"))
                    Files.readAllLines(path, charset).forEach(System.out::println);
                else
                    Files.writeString(path, text + System.lineSeparator(), charset, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}