import java.util.ArrayList;
import java.util.Scanner;

public class Character {
    private Attributes attributes;
    private int health;
    private int maxHealth;
    private Armor eqHead;
    private Armor eqBody;
    private Armor eqGloves;
    private Armor eqLegs;
    private Armor eqBoots;
    private Weapon main;
    private Weapon secondary;
    private Weapon boot;


    public Character(){
        attributes = new Attributes();
        health = 0;
        maxHealth = 0;
        eqHead = new Armor(0,0,"bowl cut",0,"Head");
        eqBody = new Armor(0,0,"bare chested",0,"Body");
        eqGloves = new Armor(0,0,"bare hands",0,"Gloves");
        eqLegs = new Armor(0,0,"bare legs",0,"Legs");
        eqBoots = new Armor(0,0,"barefoot", 0,"Boots");
        main = new Weapon(0,0,"right punch",1,1,false,"Crush");
        secondary = new Weapon(0,0,"left punch",1,1,false,"Crush");
        boot = new Weapon(0,0,"shiv",1,1,false,"Stab");
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
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Choose your placement:\nHead (0)\nBody (1)\nGloves (2)\nLegs (3)\nBoots (4)\nComplete (5)");
            int choice = scanner.nextInt();
            switch (choice){
                case 0:
                    eqHead = pickArmor("Head",armors);
                    break;
                case 1:
                    eqBody = pickArmor("Body",armors);
                    break;
                case 2:
                    eqGloves = pickArmor("Gloves",armors);
                    break;
                case 3:
                    eqLegs = pickArmor("Legs",armors);
                    break;
                case 4:
                    eqBoots = pickArmor("Boots",armors);
                    break;
            }
            if (choice == 5){
                break;
            }
        }
    }

    public Armor getEqHead() {
        return eqHead;
    }

    public Armor getEqBody() {
        return eqBody;
    }

    public Armor getEqGloves() {
        return eqGloves;
    }

    public Armor getEqLegs() {
        return eqLegs;
    }

    public Armor getEqBoots() {
        return eqBoots;
    }

    public void setEqHead(Armor armor){
        eqHead = armor;
    }

    public void setEqBody(Armor armor){
        eqBody = armor;
    }

    public void setEqGloves(Armor armor){
        eqGloves = armor;
    }

    public void setEqLegs(Armor armor){
        eqLegs = armor;
    }

    public void setEqBoots(Armor armor){
        eqBoots = armor;
    }

    public void setMain(Weapon weapon){
        main = weapon;
    }

    public void setSecondary(Weapon weapon){
        secondary = weapon;
    }

    public void setBoot(Weapon weapon){
        boot = weapon;
    }

    public void export(){
        String file  = attributes.getName()+","+attributes.getLevel()+","+attributes.getExperience()+","+attributes.getAge()+","+attributes.getWeight()+","+attributes.getHeight()+","+attributes.getMorality()+","+attributes.getStrength()+","+attributes.getCharisma()+","+attributes.getConstitution()+","+attributes.getIntelligence()+","+attributes.getDexterity()+","+attributes.getLuck()+","+attributes.getInventory().getMoney()+","+health+","+maxHealth+"\n";
        file+="<E>"+eqHead.toFile();
        file+="<E>"+eqBody.toFile();
        file+="<E>"+eqGloves.toFile();
        file+="<E>"+eqLegs.toFile();
        file+="<E>"+eqBoots.toFile();
        file+="<1>"+main.toFile();
        file+="<2>"+secondary.toFile();
        file+="<3>"+boot.toFile();
        ArrayList<Item> items = attributes.getInventory().getItems();
        for (Item item : items){
            if (item instanceof Weapon){
                Weapon weapon = (Weapon) item;
                file+="<W>"+weapon.toFile();
            }
            if (item instanceof Armor){
                Armor armor = (Armor)item;
                file+="<A>"+armor.toFile();
            }
            if (item instanceof Potion){
                Potion potion = (Potion) item;
                file+="<P>"+potion.toFile();
            }
            if (item instanceof Equipment){
                Equipment equipment = (Equipment)item;
                file+="<T>"+equipment.toFile();
            }

        }


    }
}
