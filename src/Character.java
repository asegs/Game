import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Character {
    private Attributes attributes;
    private int health;
    private int maxHealth;
    private Equipped equipped;

    Scanner scanner = new Scanner(System.in);

    public Character(){
        attributes = new Attributes();
        health = 0;
        maxHealth = 0;
        equipped = new Equipped();
    }


    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Armor pickArmor (String place,ArrayList<Armor> armors){
        Scanner scanner1 = new Scanner(System.in);
        int counter = 0;
        ArrayList<Armor> choiceArmor = new ArrayList<>();
        for (Armor armor:armors){
            if (armor.getLocation().equals(place)){
                System.out.println("("+counter+") "+armor.getName());
                choiceArmor.add(armor);
                counter++;
            }
        }
        System.out.println("Choose one of the above armors to equip:");
        return choiceArmor.get(scanner1.nextInt());
    }

    public void setArmor(){
        ArrayList<Item> armors1 = attributes.getInventory().getArmors();
        ArrayList<Armor> armors = new ArrayList<>();
        for (Item armor:armors1){
            armors.add((Armor)armor);
        }
            System.out.println("Choose your placement:\nHead (0)\nBody (1)\nGloves (2)\nLegs (3)\nBoots (4)\nComplete (5)");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    equipped.setEqHead(pickArmor("Head", armors));
                    break;
                case 1:
                    equipped.setEqBody(pickArmor("Body", armors));
                    break;
                case 2:
                    equipped.setEqGloves(pickArmor("Gloves", armors));
                    break;
                case 3:
                    equipped.setEqLegs(pickArmor("Legs", armors));
                    break;
                case 4:
                    equipped.setEqBoots(pickArmor("Boots", armors));
                    break;
            }
        }


    public void setWeapon(){
        ArrayList<Item> weapons1 = attributes.getInventory().getWeapons();
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Item weapon:weapons1){
            weapons.add((Weapon) weapon);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your placement:\nMain (0)\nSecondary (1)\nBoot (2)\nComplete (3)");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                equipped.setMain(pickWeapon(weapons));
                break;
            case 1:
                equipped.setSecondary(pickWeapon(weapons));
                break;
            case 2:
                equipped.setBoot(pickWeapon(weapons));
                break;
        }
    }

    public Weapon pickWeapon(ArrayList<Weapon> weapons){
        int counter = 0;
        for (Weapon weapon : weapons){
            System.out.println("("+counter+")"+weapon.toString());
            counter++;
        }
        System.out.println("Choose your weapon's number:");
        return weapons.get(scanner.nextInt());
    }

    public Equipped getEquipped(){
        return equipped;
    }

    public Armor getEqHead() {
        return equipped.getEqHead();
    }

    public Armor getEqBody() {
        return equipped.getEqBody();
    }

    public Armor getEqGloves() {
        return equipped.getEqGloves();
    }

    public Armor getEqLegs() {
        return equipped.getEqLegs();
    }

    public Armor getEqBoots() {
        return equipped.getEqBoots();
    }



    public void export(){
        String file  = attributes.getName()+","+attributes.getLevel()+","+attributes.getExperience()+","+attributes.getAge()+","+attributes.getWeight()+","+attributes.getHeight()+","+attributes.getMorality()+","+attributes.getStrength()+","+attributes.getCharisma()+","+attributes.getConstitution()+","+attributes.getIntelligence()+","+attributes.getDexterity()+","+attributes.getLuck()+","+attributes.getInventory().getMoney()+","+health+","+maxHealth+","+attributes.getDifficulty()+"\n";
        file+="<E>"+equipped.getEqHead().toFile()+"\n";
        file+="<E>"+equipped.getEqBody().toFile()+"\n";
        file+="<E>"+equipped.getEqGloves().toFile()+"\n";
        file+="<E>"+equipped.getEqLegs().toFile()+"\n";
        file+="<E>"+equipped.getEqBoots().toFile()+"\n";
        file+="<1>"+equipped.getMain().toFile()+"\n";
        file+="<2>"+equipped.getSecondary().toFile()+"\n";
        file+="<3>"+equipped.getBoot().toFile()+"\n";
        file+=attributes.getInventory().toFile()+"\n";
        fileEditor.replaceFile("src/Characters/char_"+getAttributes().getName()+".txt",file,false);
    }

    public void genStats(){
        setMaxHealth(getAttributes().getConstitution()*3+getAttributes().getLevel()*5+10);
        setHealth(getMaxHealth());
    }
}
