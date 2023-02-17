package Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task1 {
    static String title = "select * from students where ";

    public static void main(String[] args) throws IOException {
        String[] jsonString = readJsonString();
        String[] condition = makeContidion(jsonString);;
        String request = makeRequest(condition);
        System.out.println(request);

    }

    static String[] readJsonString() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file.json"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.equals("{") && !line.equals("}")) {
                sb.append(line);
            }
        }
        br.close();
        return sb.toString().trim()
                .replace('"', ' ')
                .replaceAll(" ", "")
                .replaceAll(":", " = ")
                .split(",");

    }


    static String[] makeContidion(String[] values){
        StringBuilder condition = new StringBuilder();
        for (String value : values) {
            if (!value.contains("null"))
                condition.append(value).append(",");
        }
        return condition.toString().split(",");
    }

    static String makeRequest(String[] condition) {
        StringBuilder sb = new StringBuilder(title);
        int i = 0;
        int size = condition.length;
        for (String s : condition) {
            sb.append(s);
            if (i < size - 1) {
                sb.append(" and ");
                i++;
            }
        }
        return sb.toString();
    }
}
