package Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/*
Дана json строка (можно сохранить в файл и читать из файла)

[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]

Написать метод(ы), который распарсит json и, используя StringBuilder,
создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].

Пример вывода:

Студент Иванов получил 5 по предмету Математика.

Студент Петрова получил 4 по предмету Информатика.

Студент Краснов получил 5 по предмету Физика.
 */
public class Task3 {
    static String[] attributes = {"фамилия", "оценка", "предмет"};
    static String[] requestAttributes = {"Студент", "получил", "по предмету"};

    public static void main(String[] args) throws IOException {
        String[][] jsonString = readJson();
        String[][] refString = refactorStrings(jsonString);
        String[] request = makeRequest(refString);
        showArray(request);
    }
    static void showArray(String[] array){
        for (String item: array) {
            System.out.println(item);
        }
    }
    static String[][] readJson() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file.json"));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null){
            if (line.contains(attributes[0])){
                count++;
            }
        }
        String[][] students = new String[count][attributes.length];
        int i = -1;
        int j = 0;;
        br = new BufferedReader(new FileReader("file.json"));
        while ((line = br.readLine()) != null) {
            if (!line.contains("{") && !line.contains("}")
                    && !line.contains("[") && !line.contains("]")
                    && line.length()>1){
                if (line.contains(attributes[0])){
                    i++;
                    j = 0;
                }
                students[i][j] = line.trim();
                j++;
            }
        }
        return students;
    }
    static String[][] refactorStrings(String[][] values){
        for (int i = 0; i < values.length; i++)
            for (int j = 0; j < attributes.length; j++) {
                values[i][j] = values[i][j].replaceAll(attributes[j], "")
                        .replaceAll(": ", "")
                        .replace('"', ' ').replace(',', ' ').trim();
                if (j == 2)
                    values[i][j] = values[i][j].concat(".");
            }
        return values;
    }

    static String[] makeRequest(String[][] values){
        String[] requests = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < attributes.length; j++)
                sb.append(requestAttributes[j]).append(" ").append(values[i][j]).append(" ");
            requests[i] = sb.toString();
        }
        return requests;
    }

}
