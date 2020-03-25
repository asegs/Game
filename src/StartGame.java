import java.util.Scanner;

public class StartGame {
    Scanner scanner = new Scanner(System.in);
    public Character titleScreen(){
        System.out.println("Geas\nBy AJS");
        Character character = new Character();
        while (true){
            System.out.println("Do you want to start a new game ('N'), load a game ('L'), view stats ('S'), or quit ('Q'):");
            String choice = scanner.nextLine();
            if (choice.equals("N")){
                Attributes attributes = newCharacter.buildCharacter();
                character.setAttributes(attributes);
                character.genStats();
                character.export();
                fileEditor.addToFile("src/char_names.txt",character.getAttributes().getName(),false);
                break;
            }
            if (choice.equals("L")){
                character = loadChar();
                break;
            }
            if (choice.equals("S")){
                break;
            }
            if (choice.equals("Q")){
                System.out.println("Goodbye, traveller.");
                break;
            }
        }
        return character;
    }

    public Character loadChar(){
        String[] characters = fileReader.reader("src/char_names.txt","\n").split("\n",0);
        System.out.println("Choose a character by number from the list below:");
        for (int i = 0;i<characters.length;i++){
            System.out.println("("+i+") "+characters[i]);
        }
        int charName = scanner.nextInt();
        return ImportCharacter.impChar("src/Characters/char_"+characters[charName]+".txt");

    }

//    public static void main(String[] args) throws InterruptedException {
//        StartGame startGame = new StartGame();
//        Character character = startGame.titleScreen();
//        EnemyNPC enemyNPC = new EnemyNPC();
//        int counter = 0;
//        Life life = new Life();
//        life.hub(character);
//        while (character.getHealth()>0){
//            enemyNPC = enemyNPC.randomNPC(character);
//            Fight.duel(character,enemyNPC);
//            counter++;
//            System.out.println("You have killed "+counter+" people.");
//        }
//    }
}
