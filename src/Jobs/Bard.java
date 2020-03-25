package Jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bard {
    Random random = new Random();
    public int interact() {
        int pay = random.nextInt(10)+15;
        Scanner scanner = new Scanner(System.in);
        String[] happyWordPrim = new String[]{"love","peace","joy","happy","beauty","kind"};
        ArrayList<String> happyWords = new ArrayList<>(Arrays.asList(happyWordPrim));
        String[] powerWordPrim = new String[]{"strong","might","mighty","kill","crush","conquer","win","rule"};
        ArrayList<String> powerWords = new ArrayList<>(Arrays.asList(powerWordPrim));
        String[] sadWordPrim = new String[]{"loss","death","sorrow","suffer","cry","tears","sob","sad"};
        ArrayList<String> sadWords = new ArrayList<>(Arrays.asList(sadWordPrim));
        String[] feelings = new String[]{"happy","powerful","sad"};
        String[] faces = new String[]{"O","^","_"," ","U"};
        String[] eyes = new String[]{"\\   /","_  _","-  -","o  o","O  O"};
        Random random = new Random();
        int feeling = random.nextInt(3);
        int appreciation = 2;
        String eye = eyes[appreciation];
        String face = faces[appreciation];
        while (true) {
            String kingASCII = "                     ____\n" +
                    "                     / ___`\\\n" +
                    "         /|         ( (   \\ \\\n" +
                    "    |^v^v  V|        \\ \\/) ) )\n" +
                    "    \\  ____ /         \\_/ / /\n" +
                    "    ,Y`    `,            / /\n" +
                    "    ||  "+eye+")           { }\n" +
                    "    \\\\   _\\ |           | |\n" +
                    "     \\\\ / " + face + "`\\_         / /\n" +
                    "     / |  ~ | ``\\     _|_|\n" +
                    "  ,-`  \\    |  \\ \\  ,//(_}\n" +
                    " /      |   |   | \\/  \\| |\n" +
                    "|       |   |   | '   ,\\ \\\n" +
                    "|     | \\   /  /\\  _/`  | |\n" +
                    "\\     |  | |   | ``     | |\n" +
                    " |    \\  \\ |   |        | |\n" +
                    " |    |   |/   |        / /\n" +
                    " |    |        |        | |\n";
            System.out.println(kingASCII);
            System.out.println("(King): I want to feel "+feelings[feeling]+" today.  Will you do that?");
            System.out.println("Tell a tale for the king:");
            String story = scanner.nextLine();
            story = story.toLowerCase();
            String[] words = story.split(" ",0);
            int sadScore = 0;
            int happyScore = 0;
            int powerScore = 0;
            for (String word : words) {
                if (sadWords.contains(word)) {
                    sadScore += 1;
                }
                if (happyWords.contains(word)) {
                    happyScore += 1;
                }
                if (powerWords.contains(word)) {
                    powerScore += 1;
                }
            }
            switch (feeling){
                case 0:
                    appreciation = appreciation+happyScore-sadScore-powerScore;
                    if (happyScore>sadScore+powerScore){
                        System.out.println("Yes yes! Jolly jolly!");
                    }
                    if (sadScore>powerScore+happyScore){
                        System.out.println("You are quite dark today.  I suddenly feel blue.");
                    }
                    if (powerScore>sadScore+happyScore){
                        System.out.println("I do not need to conquer today, just to enjoy the air.");
                    }
                    break;
                case 1:
                    appreciation = appreciation + powerScore-sadScore-happyScore;
                    if (powerScore>happyScore+sadScore){
                        System.out.println("Yes...yes...mwahahahaha!");
                    }
                    if (sadScore>happyScore+powerScore){
                        System.out.println("Try something more optimistic, will you?");
                    }
                    if (happyScore>sadScore+powerScore){
                        System.out.println("Wipe that smile off your face!");
                    }
                    break;
                case 2:
                    appreciation = appreciation +sadScore-powerScore-happyScore;
                    if (sadScore>happyScore+powerScore){
                        System.out.println("Yes..that touches my soul.  Thank you good friend.");
                    }
                    if (happyScore>powerScore+sadScore){
                        System.out.println("That's quite off color, considering the way things have been lately.");
                    }
                    if (powerScore>happyScore+sadScore){
                        System.out.println("I'm in no mood for tales of bloodshed now.");
                    }
                    break;
            }
            if (appreciation>4){
                appreciation=4;
            }
            if (appreciation<0){
                appreciation=0;
            }
            eye = eyes[appreciation];
            face = faces[appreciation];
            kingASCII = "                     ____\n" +
                    "                     / ___`\\\n" +
                    "         /|         ( (   \\ \\\n" +
                    "    |^v^v  V|        \\ \\/) ) )\n" +
                    "    \\  ____ /         \\_/ / /\n" +
                    "    ,Y`    `,            / /\n" +
                    "    ||  "+eye+")           { }\n" +
                    "    \\\\   _\\ |           | |\n" +
                    "     \\\\ / " + face + "`\\_         / /\n" +
                    "     / |  ~ | ``\\     _|_|\n" +
                    "  ,-`  \\    |  \\ \\  ,//(_}\n" +
                    " /      |   |   | \\/  \\| |\n" +
                    "|       |   |   | '   ,\\ \\\n" +
                    "|     | \\   /  /\\  _/`  | |\n" +
                    "\\     |  | |   | ``     | |\n" +
                    " |    \\  \\ |   |        | |\n" +
                    " |    |   |/   |        / /\n" +
                    " |    |        |        | |\n";
            if (appreciation==0){
                System.out.println(kingASCII);
                System.out.println("Get out of my court!");
                System.out.println("You are paid 0 dollars.");
                return 0;
            }
            if (appreciation==4){
                System.out.println(kingASCII);
                System.out.println("Thank you very much, do come back!");
                System.out.println("You are paid "+pay+" dollars!");
                return pay;
            }
        }
    }


//    public static void main(String[] args) {
//        Bard bard = new Bard();
//        bard.interact();
//    }
}
