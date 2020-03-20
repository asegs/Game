package Jobs;

import java.util.Random;
import java.util.Scanner;

public class Cleaner {
    public String[][] stringBuilder(int width,int height,int cleanliness){
        Random random = new Random();
        String[][] floor = new String[width][height];
        for (int i =0;i<width;i++){
            for (int b = 0;b<height;b++){
                int chance = random.nextInt((width*height));
                if (chance%cleanliness==0){
                    floor[i][b] = "X";
                }else {
                    floor[i][b] = " ";
                }
            }
        }
        return floor;
    }

    public void draw(String[][] floor){
        System.out.print(" ");
        for (int i = 0;i<floor[0].length;i++){
            System.out.print(i+" ");
        }
        System.out.print("\n");
        int counter = 0;
        for (String[] row:floor){
            System.out.print(counter);
            for (String letter: row){
                System.out.print(letter+" ");
            }
            counter++;
            System.out.print("\n");
        }
    }

    public void cleanup(String[][] floor){
        int pay = 0;
        Scanner scanner = new Scanner(System.in);
        boolean clean = false;
        while (!clean){
            draw(floor);
            clean = true;
            for (String[] row : floor){
                for (String letter : row){
                    if (letter.equals("X")){
                        clean = false;
                        break;
                    }
                }
            }
            if (!clean) {
                System.out.println("Enter the space you want to clean, separated by a comma:");
                String space = scanner.nextLine();
                String[] choice = space.split(",", 0);
                floor[Integer.parseInt(choice[0])][Integer.parseInt(choice[1])] = " ";
                pay+=3;
            }


        }
        System.out.println("The floor is clean.  You are paid "+pay+" dollars.");
    }

}
