import Jobs.Bard;
import Jobs.Cleaner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Life {
    Scanner scanner = new Scanner(System.in);
    public void hub(Character character) throws InterruptedException {
        int toDo = 0;
        while (toDo>=0&&toDo<=6) {
            System.out.println("You have "+character.getAttributes().getInventory().getMoney()+" dollars.");
            System.out.println("Do you want to go shopping (0), go to work (1), duel (2), set your loadout (3), view your loadout (4), upgrade your loadout (5). or save/quit (6):");
            toDo = scanner.nextInt();
            switch (toDo){
                case 0:
                    ArrayList<Merchant> merchants = new ArrayList<>();
                    String[] merchs = fileReader.reader("src/merch_names.txt","\n").split("\n",0);
                    for (String name : merchs){
                        merchants.add(ImportCharacter.impMerch(name));
                    }
                    int counter = 0;
                    for (Merchant merchant : merchants){
                        System.out.println("("+counter+") "+merchant.getName()+": "+merchant.getDollars()+" dollars");
                    }
                    System.out.println("Enter the number of the merchant to buy from:");
                    Merchant merchant = merchants.get(scanner.nextInt());
                    if (merchant.getInventory().getItems().size()<10) {
                        merchant.stock(5);
                    }
                    System.out.println("Do you want to buy (0) or sell (1)?:");
                    int choiceSales = scanner.nextInt();
                    if (choiceSales==0) {
                        Item item = merchant.browseInventory();
                        merchant.buy(character,item);
                        merchant.setDollars(item.getValue() + merchant.getDollars());
                    }
                    if (choiceSales==1){
                        merchant.sellTo(character);
                    }
                    merchant.writeMerchant();
                    break;
                case 1:
                    System.out.println("Would you like to do bard work (0) or cleaning work (1):");
                    int choice = scanner.nextInt();
                    if (choice==0){
                        Bard bard = new Bard();
                        character.getAttributes().getInventory().addMoney(bard.interact());
                    }
                    if (choice==1){
                        Cleaner cleaner = new Cleaner();
                        String[][] floor = cleaner.stringBuilder(10,10,10);
                        character.getAttributes().getInventory().addMoney(cleaner.cleanup(floor));
                    }
                    break;
                case 2:
                    EnemyNPC enemyNPC = new EnemyNPC();
                    enemyNPC = enemyNPC.randomNPC(character);
                    Fight.duel(character,enemyNPC);
                    break;
                case 3:
                    character.setArmor();
                    character.setWeapon();
                    break;
                case 4:
                    System.out.println(character.getEquipped().toString());
                    break;
                case 5:
                    Smithing smithing =  new Smithing();
                    Weapon weapon = (smithing.upgradeWeapon(character.getEquipped().getMain()));
                    if (character.getAttributes().getInventory().getMoney()>weapon.getValue()){
                        character.getAttributes().getInventory().spendMoney(weapon.getValue());
                        character.getEquipped().setMain(weapon);
                    }
                    else {
                        System.out.println("Sorry, you cannot afford that.");
                    }
                    break;
                case 6:
                    character.export();
                    System.out.println("See you in another lifetime.");
                    System.exit(0);
            }
            character.export();
        }
        }

    public static void main(String[] args) throws InterruptedException {
        Life life = new Life();
        StartGame startGame = new StartGame();
        Character character = startGame.titleScreen();
        life.hub(character);
    }
}
