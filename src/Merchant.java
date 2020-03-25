import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Merchant {
    private String name;
    private int dollars;
    private Inventory inventory;
    private int relationship;

    Scanner scanner = new Scanner(System.in);

    public Merchant(int dollars,Inventory inventory,int relationship,String name){
        this.dollars = dollars;
        this.inventory = inventory;
        this.relationship = relationship;
        this.name = name;
    }

    public Item barter(Item item){
        while (true){
            int idealPrice = (int) item.getValue()*(15-relationship)/10;
            if (idealPrice<(0.5*item.getValue())){
                idealPrice = (int)(0.5 * item.getValue());
            }
            double reallyLow = idealPrice*0.6;
            double low = idealPrice*0.9;
            System.out.println("The "+item.getName()+" costs "+idealPrice+".  How much do you offer?");
            int offer = scanner.nextInt();

            if (offer<reallyLow){
                System.out.println("That is simply preposterous.");
                relationship-=2;
            }
            else if (offer<low){
                System.out.println("That is too low for my taste.");
                relationship-=1;
            }
            else if (offer>=idealPrice){
                System.out.println("Why, marvelous!");
                item.setValue(offer);
                relationship+=1;
                break;

            }
            else if(offer>=low){
                System.out.println("I will accept such an offer.");
                item.setValue(offer);
                break;
            }
            else if(offer == 0){
                System.out.println("Maybe another time.");
                break;
            }

        }
        return item;
    }

    public void stock(int n){
        for (int i = 0;i<n;i++){
            inventory.addToInventory(RandomItems.randomWeapon(-1));
            inventory.addToInventory(RandomItems.randomArmor("",-1));
            writeMerchant();
        }
    }



    public void buy(Character character, Item item){
        if (character.getAttributes().getInventory().getMoney()>=item.getValue()){
            character.getAttributes().getInventory().spendMoney(item.getValue());
            character.getAttributes().getInventory().addToInventory(item);
            this.setDollars(dollars+item.getValue());
            this.inventory.removeFromInventory(item);
        }else {
            System.out.println("You cannot afford that.");
        }

    }

    public void sell(Character character, Item item){
        if (this.getDollars()>item.getValue()){
            character.getAttributes().getInventory().removeFromInventory(item);
            character.getAttributes().getInventory().addMoney(item.getValue());
            this.setDollars(dollars-item.getValue());
            this.inventory.addToInventory(item);
        }
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public void displayInventory(ArrayList<Item> itemList){
        int counter = 0;
        for (Item item:itemList){
            if (item instanceof Weapon){
                Weapon weapon = (Weapon) item;
                System.out.println("("+counter+") "+weapon.toString());
            }
            if (item instanceof Armor){
                Armor armor = (Armor) item;
                System.out.println("("+counter+") "+armor.toString());
            }
            if (item instanceof Potion){
                Potion potion = (Potion) item;
                System.out.println("("+counter+") "+potion.toString());
            }
            if (item instanceof Equipment){
                Equipment equipment = (Equipment) item;
                System.out.println("("+counter+") "+equipment.toString());
            }
            counter++;
        }
    }

    public Item browseInventory(){
        if(getRelationship()<3&&getRelationship()>-3){
            System.out.println("Welcome to my shop.");
        }
        else if (getRelationship()>=3){
            System.out.println("Good to see you again!");
        }
        else if (getRelationship()<=-3){
            System.out.println("Oh, you again.");
        }
        Scanner scanner = new Scanner(System.in);
        displayInventory(this.inventory.getItems());
        Item finalItem = new Weapon(0,0,"empty",0,0,false,"empty");
            System.out.println("Choose an item from the list to buy, or enter 'A' for armor, 'W' for weapons, 'P' for potions, 'E' for equipment, or 'Q' to quit:");
            String choice = scanner.nextLine();
            if (isNumeric(choice)){
                int itemNum = Integer.parseInt(choice);
                finalItem = barter(this.inventory.getItems().get(itemNum));
            }
            else{
                switch (choice){
                    case "A":
                        ArrayList<Item> armors = this.inventory.getArmors();
                        displayInventory(armors);
                        if (armors.size()==0){
                            System.out.println("This merchant has no armor.");
                            finalItem=browseInventory();
                            break;
                        }
                        System.out.println("Choose an item from the list to buy:");
                        int armorChoice = scanner.nextInt();
                        Item armorItem = armors.get(armorChoice);
                        finalItem = barter(armorItem);
                        break;
                    case "W":
                        ArrayList<Item> weapons = this.inventory.getWeapons();
                        displayInventory(weapons);
                        if (weapons.size()==0){
                            System.out.println("This merchant has no weapons.");
                            finalItem=browseInventory();
                            break;
                        }
                        System.out.println("Choose an item from the list to buy:");
                        int weaponChoice = scanner.nextInt();
                        Item weaponItem = weapons.get(weaponChoice);
                        finalItem = barter(weaponItem);
                        break;
                    case "P":
                        ArrayList<Item> potions = this.inventory.getPotions();
                        displayInventory(potions);
                        if (potions.size()==0){
                            System.out.println("This merchant has no potions.");
                            finalItem=browseInventory();
                            break;
                        }
                        System.out.println("Choose an item from the list to buy:");
                        int potionChoice = scanner.nextInt();
                        Item potionItem = potions.get(potionChoice);
                        finalItem = barter(potionItem);
                        break;
                    case "E":
                        ArrayList<Item> equipment = this.inventory.getEquipment();
                        displayInventory(equipment);
                        if (equipment.size()==0){
                            System.out.println("This merchant has no equipment.");
                            finalItem=browseInventory();
                            break;
                        }
                        System.out.println("Choose an item from the list to buy:");
                        int equipChoice = scanner.nextInt();
                        Item equipItem = equipment.get(equipChoice);
                        finalItem = barter(equipItem);
                        break;
                    case "Q":
                        break;


                }
            }
            return finalItem;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void sellTo(Character character){
        int counter = 0;
        for (Item item : character.getAttributes().getInventory().getItems()){
            System.out.println("("+counter+") "+item.getName()+", "+item.getValue());
            counter++;
        }
        System.out.println("Which number item do you wish to sell?:");
        Item item = character.getAttributes().getInventory().getItems().get(scanner.nextInt());
        int value = (int)(item.getValue()*((5.0+getRelationship())/10.0));
        if (value>item.getValue()*1.25){
            value = (int)(item.getValue()*1.25);
        }
        if (this.getDollars()>=value){
            System.out.println(getName()+" offers to buy your "+item.getName()+" for "+value+" dollars.");
            System.out.println("Y/N");
            String entry = scanner.next();
            if (entry.equals("N")){
                System.out.println("No sale.");
            }
            else {
                this.setDollars(this.getDollars() - value);
                character.getAttributes().getInventory().setMoney(character.getAttributes().getInventory().getMoney() + value);
                character.getAttributes().getInventory().getItems().remove(item);
                this.getInventory().addToInventory(item);
            }
        }
    }

    public void writeMerchant(){
        String file = name+","+getDollars()+","+relationship+"\n";
        file+=inventory.toFile();
        fileEditor.replaceFile("src/Merchants/merc_"+name+".txt",file,false);
    }



//    public static void main(String[] args) {
//        Merchant merchant = new Merchant(0,new Inventory(),0,"Jim");
//        merchant.inventory.getItems().add(RandomItems.randomWeapon(3));
//        merchant.inventory.getItems().add(RandomItems.randomArmor("Body",3));
//        merchant.browseInventory();
//        merchant.writeMerchant();
//        EnemyNPC enemyNPC = new EnemyNPC();
//        Character character = ImportCharacter.impChar("src/Characters/char_John.txt");
//        enemyNPC = enemyNPC.randomNPC(character);
//        System.out.println(enemyNPC.getEquipped().getEqHead().toString());
//        System.out.println(enemyNPC.getInventory().getMoney());
//
//
//    }
}
