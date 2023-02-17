package Task4;



import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

//4*. Реализуйте простой калькулятор, с консольным интерфейсом.
// К калькулятору добавить логирование.
public class Task4 {
    public static void main(String[] args) throws IOException {
        printMenu();
        System.out.print("Выберите операцию: ");
        int operator = getInt();
        System.out.print("Первое число:");
        int num1 = getInt();
        System.out.print("Второе число: ");
        int num2 = getInt();
        System.out.println("Результат операции равен: " + calc(num1, num2, operator));



    }
    static Scanner in = new Scanner(System.in);
    static void printMenu(){
        System.out.println("Калькулятор\n" +
                            "Список оптий:\n" +
                            "1- Сложение\n" +
                            "2- Вычитание\n" +
                            "3- Умножение\n" +
                            "4- Деление\n" +
                            "*Калькулятор работает только с натуральными числами*");
    }

    static int getInt(){
        int num;
        if (in.hasNextInt()) num = in.nextInt();
        else {
            System.out.println("Ошибка при вводе");
            in.next();
            num = getInt();
        }
        return num;
    }
    static int calc(int num1, int num2, int operator) throws IOException {
        Logger logger = Logger.getLogger(Task4.class.getName());
        FileHandler fh = new FileHandler("log.xml");
        logger.addHandler(fh);
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
        int result = 0;
        switch (operator){
            case 1:
                result = num1 + num2;
                logger.log(Level.INFO, "Сложение; Результат: " + result);
                break;
            case 2:
                result = num1 - num2;
                logger.log(Level.INFO, "Вычитание; Результат: " + result);
                break;
            case 3:
                result = num1*num2;
                logger.log(Level.INFO, "Умножение; Результат: " + result);
                break;
            case 4:
                result = num1 / num2;
                logger.log(Level.INFO, "Деление; Результат: " + result);
                break;
            default:
                System.out.println("Операция не распознана, повторите ввод");
                logger.log(Level.WARNING, "Ошибка операции");
                calc(num1, num2, getInt());
        }
        return result;
    }

}
