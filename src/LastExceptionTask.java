import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LastExceptionTask {

    // Spasskih Dmitryi Dmitrievich 23.06.2001 0958813638 m
    public static void main(String[] args) {
        inputDate();

    }

    public static void inputDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your surname, name, patronymic, birthdate(dd.mm.yyyy), phone number and sex('m' or 'f') by a space, please: ");
        String date = scanner.nextLine();

        String[] splitDate = date.split(" ");

        if (splitDate.length != 6) {
            throw new RuntimeException("You have entered more or less data than required.");
        }

        // Обработка birthdate
        String birthStr = splitDate[3];
        if (birthStr.length() != 10) throw new RuntimeException("Birthdate format is incorrect.");
        if (birthStr.charAt(2) != '.' || birthStr.charAt(5) != '.') throw new RuntimeException("Birthdate format is incorrect (Check the dots).");

        char[] birthArray = birthStr.toCharArray();
        for (int i = 0; i < birthArray.length; i++) {
            if (birthArray[i] != '.'){
                if (Character.digit(birthArray[i], 10) == -1) throw new RuntimeException("Birthdate format is incorrect (Check the numbers).");
            }
        }

        // Обработка phone number
        String phoneNumberStr = splitDate[4];
        try {
            Integer.parseInt(phoneNumberStr);
        } catch (NumberFormatException e) {
            System.out.println("Phone number format is incorrect (Check the numbers).");
        }

        // Обработка sex
        String sexStr = splitDate[5];
        if (sexStr.equals("m") || sexStr.equals("f")) {
        } else {
            throw new RuntimeException("Sex format is incorrect (Check the symbol).");
        }

        // Создание файла и запись в файл
        String surnameStr = splitDate[0];
        try {
            File file = new File("C:/Users/Lenovo/IdeaProjects/Exceptions/src", surnameStr);
            if (file.createNewFile()) {
                System.out.println("'" + file.getName() + "'" +  " file created :)");
                try (FileWriter writer = new FileWriter(file.getName(), true)){
                    for (String c : splitDate) {
                        writer.write("<" + c + ">");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try (FileWriter writer = new FileWriter(file.getName(), true)){
                    writer.write("\n");
                    for (String s : splitDate) {
                        writer.write("<" + s + ">");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("'" + file.getName() + "'" + " file not created because it already exists. " +
                        "Data is added to the file :) ");
            }
        } catch (IOException e) {
            System.out.println("The IOException came to us, oops.");
            e.printStackTrace();
        }
    }
}
