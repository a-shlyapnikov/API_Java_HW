package Task2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Task2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int[]array = initArray(scanner.nextInt());
        printArray(array);
        int[] sortArray = bubbleSort(array);
        printArray(sortArray);

    }

    static int[] initArray(int size){
        int[] result = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = r.nextInt(100);
        }
        return result;
    }
    static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }

    static int[] bubbleSort(int[] array) throws IOException {
        Logger logger = Logger.getLogger(Task2.class.getName());
        FileHandler fh = new FileHandler("log.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            logger.log(Level.INFO, Arrays.toString(array));
        }
        return array;
    }
}