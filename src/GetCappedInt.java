import java.util.Scanner;

public class GetCappedInt {
    public static int capped(String question, int min, int max){
        Scanner scanner1 = new Scanner(System.in);
        int num = -1000000;
        while (num <min||num>max){
            System.out.println(question);
            num = scanner1.nextInt();
        }
        return num;
    }
}
