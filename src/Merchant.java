import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Merchant {
    private int dollars;
    private Inventory inventory;
    private int relationship;

    public Merchant(int dollars,Inventory inventory,int relationship){
        this.dollars = dollars;
        this.inventory = inventory;
        this.relationship = relationship;
    }

    public Item barter(Item item){
        Scanner scanner = new Scanner(System.in);
        while (true){
            int idealPrice = (int) item.getValue()*(15-relationship)/10;
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

    public int getValFile(int pos,double mod,String[] weaponAtts){
        return (int)(Integer.parseInt(weaponAtts[pos])*mod);
    }

    public Weapon makeWeapon(String prefix,double dmgMod,double wgtMod,double valMod,double rangeMod,String[] weaponAtts,boolean twoHanded){
        String name = prefix+weaponAtts[0];
        int damage = getValFile(1,dmgMod,weaponAtts);
        int weight = getValFile(2,wgtMod,weaponAtts);
        int value = getValFile(3,valMod,weaponAtts);
        int range = getValFile(4,rangeMod,weaponAtts);
        if (range<1){
            range = 1;
        }
        return new Weapon(value,weight,name,damage,range,twoHanded,weaponAtts[5]);
    }

    public void stockWeapons(int n){
        Random random = new Random();
        String file = fileReader.reader("src/Base Equipment/base_weapons.txt","\n");
        String[] weapons = file.split("\n",0);
        for (int i = 0;i<n;i++){
            int size = random.nextInt(4);
            String weapon = weapons[random.nextInt(weapons.length)];
            String[] weaponAtts = weapon.split(",",0);
            Weapon weapon1 =  new Weapon(0,0,"empty",0,0,false,"empty");
            switch (size){
                case 0:
                    weapon1 = makeWeapon("Concealed ",0.5,0.33,0.45,0.5,weaponAtts,false);
                    break;
                case 1:
                    weapon1 = makeWeapon("Light ",0.75,0.8,0.9,0.75,weaponAtts,false);
                    break;
                case 2:
                    weapon1 = makeWeapon("Standard ",1,1,1,1,weaponAtts,true);
                    break;
                case 3:
                    weapon1 = makeWeapon("Great ",1.5,1.75,1.6,1.4,weaponAtts,true);
                    break;
            }
            this.inventory.addToInventory(weapon1);
        }
    }

    public Armor makeArmor(String prefix,double defMod,double wgtMod,double valMod,String placement,String[] armorAtts){
        String name = prefix+armorAtts[0];
        int def = getValFile(1,defMod,armorAtts);
        int weight = getValFile(2,wgtMod,armorAtts);
        int value = getValFile(3,valMod,armorAtts);
        return new Armor(value,weight,name,def,placement);
    }

    public void stockArmors(int n){
        Random random = new Random();
        String file = fileReader.reader("src/Base Equipment/base_armors.txt","\n");
        String[] armors = file.split("\n",0);
        for (int i = 0;i<n;i++){
            int material = random.nextInt(4);
            String armor = armors[random.nextInt(armors.length)];
            String[] armorAtts = armor.split(",",0);
            Armor armor1 = new Armor(0,0,"empty",0,"empty");
            switch (material){
                case 0:
                    armor1 = makeArmor("Cloth ",0.4,0.35,0.35,armorAtts[4],armorAtts);
                    break;
                case 1:
                    armor1 = makeArmor("Leather ",0.75,0.5,0.65,armorAtts[4],armorAtts);
                    break;
                case 2:
                    armor1 = makeArmor("Chainmail ",1,1,1,armorAtts[4],armorAtts);
                    break;
                case 3:
                    armor1 = makeArmor("Steel ",1.6,1.85,1.75,armorAtts[4],armorAtts);
                    break;
            }
            this.getInventory().addToInventory(armor1);
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
                        System.out.println("Choose an item from the list to buy:");
                        int armorChoice = scanner.nextInt();
                        Item armorItem = armors.get(armorChoice);
                        finalItem = barter(armorItem);
                        break;
                    case "W":
                        ArrayList<Item> weapons = this.inventory.getWeapons();
                        displayInventory(weapons);
                        System.out.println("Choose an item from the list to buy:");
                        int weaponChoice = scanner.nextInt();
                        Item weaponItem = weapons.get(weaponChoice);
                        finalItem = barter(weaponItem);
                        break;
                    case "P":
                        ArrayList<Item> potions = this.inventory.getPotions();
                        displayInventory(potions);
                        System.out.println("Choose an item from the list to buy:");
                        int potionChoice = scanner.nextInt();
                        Item potionItem = potions.get(potionChoice);
                        finalItem = barter(potionItem);
                        break;
                    case "E":
                        ArrayList<Item> equipment = this.inventory.getEquipment();
                        displayInventory(equipment);
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

    public static void main(String[] args) {
        Merchant merchant = new Merchant(0,new Inventory(),0);
        merchant.stockWeapons(5);
        merchant.stockArmors(5);
        merchant.browseInventory();


    }
}
