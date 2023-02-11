import java.util.Scanner;

public class Solution {

    public static int TriangleNumb(int number){
        int result = 1;
        result = (number*(number+1))/2;
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер треугольного числа: ");
        int userNumber = in.nextInt();
        int triangleNumber = TriangleNumb(userNumber);
        System.out.printf("Треугольное число с номером %d равно %d", userNumber, triangleNumber);
    }
}

