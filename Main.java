import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final int field_number = 6;

    public static void main(String[] args){
        System.out.println("Введите данны по порядку: Фамилия, Имя, Отчество, дата_рождения(в формате дд.мм.гггг), номер_телефона, пол(m - муж, f - жен)");
        Scanner scanner = new Scanner((System.in));
        String input = scanner.nextLine();
        scanner.close();
        String[] fields = input.split(" ");
        if (fields.length != field_number) {
            System.err.println("Неверное количество полей, вы ввели " + fields.length + ", введите 6 полей!");
        }
        String lastName = fields[0];
        String firstName = fields[1];
        String middleName = fields[2];
        LocalDate birthDate;

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(fields[3], formatter);
        } catch(DateTimeParseException e){
            System.out.println("Неверный формат даты");
            return;
        }
        long phoneNumber;
        try {
            phoneNumber =  Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона");
            return;
        }
        String gender = fields[5];
        if ((!"m".equals(gender)) && (!"f".equals(gender))){
            System.out.println("Неверный формат пола, введите f или m");
            return;
        }

        String fileName = lastName + ".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(lastName + " "+   firstName + " " + middleName + " " + birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + phoneNumber + " " + gender);
            writer.newLine();
            System.out.println("Сохранено");

        } catch (IOException e){
            System.out.println("Ошибка записи!");
        }

    }
}