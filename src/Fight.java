import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static boolean duel(Character character,EnemyNPC enemyNPC) throws InterruptedException {
        boolean charWins = false;
        while (character.getHealth()>0&&enemyNPC.getHealth()>0){
            System.out.println(character.getAttributes().getName()+"'s turn. ("+character.getAttributes().getName()+" health: "+character.getHealth()+"   "+enemyNPC.getName()+" health: "+enemyNPC.getHealth()+")");
            enemyNPC.setHealth(enemyNPC.getHealth()-attackEnemy(character,enemyNPC));
            if (enemyNPC.getHealth()<0){
                charWins = true;
                System.out.println(character.getAttributes().getInventory().getItems().size());
                loot(character,enemyNPC);
                character.export();
                break;
            }
            System.out.println(enemyNPC.getName()+"'s turn. ("+character.getAttributes().getName()+" health: "+character.getHealth()+"   "+enemyNPC.getName()+" health: "+enemyNPC.getHealth()+")");
            character.setHealth(character.getHealth()-enemyAttack(character,enemyNPC));
        }
        if (!charWins){
            System.out.println(enemyNPC.getName()+" killed you.\n\n");
            character.getAttributes().getInventory().setMoney(character.getAttributes().getInventory().getMoney()-enemyNPC.getInventory().getMoney());
            character.setHealth(character.getMaxHealth());
        }
        return charWins;
    }

    public static int attackEnemy(Character character,EnemyNPC enemyNPC) throws InterruptedException {
        Armor[] places = new Armor[]{enemyNPC.getEquipped().getEqHead(),enemyNPC.getEquipped().getEqBody(),enemyNPC.getEquipped().getEqGloves(),enemyNPC.getEquipped().getEqLegs(),enemyNPC.getEquipped().getEqBoots()};
        System.out.println("(0) "+character.getEquipped().getMain().toString()+"\n(1) "+character.getEquipped().getSecondary().toString()+"\n(2) "+character.getEquipped().getBoot().toString());
        System.out.println("Enter the number of your attack weapon:");
        Weapon weapon;
        int attackNum = scanner.nextInt();
        if (attackNum==1){
            weapon = character.getEquipped().getSecondary();
        }
        else if (attackNum==2){
            weapon = character.getEquipped().getBoot();
        }
        else {
            weapon = character.getEquipped().getMain();
        }
        System.out.println("Do you want to attack the head (0), the body (1), the hands (2), the legs (3), the feet (4), or attempt a complex maneuver (5):");
        int attack = scanner.nextInt();
        int odds = random.nextInt(120);
        int damage = 0;
        switch (attack){
            case 0:
                damage = attackAttempt(character,enemyNPC,20,0,odds,weapon,places);
                break;
            case 1:
                damage = attackAttempt(character,enemyNPC,0,1,odds,weapon,places);
                break;
            case 2:
                damage = attackAttempt(character,enemyNPC,25,2,odds,weapon,places);
                break;
            case 3:
                damage = attackAttempt(character,enemyNPC,0,3,odds,weapon,places);
                break;
            case 4:
                damage = attackAttempt(character,enemyNPC,15,4,odds,weapon,places);
                break;
            case 5:
                complexAttack(character,enemyNPC,weapon);
                damage = 0;
        }
        return damage;

    }
    public static int attackAttempt(Character character, EnemyNPC enemyNPC, int loss, int index, int odds, Weapon weapon, Armor[] places){
        int damage = 0;
        if ((20-weapon.getWeight())+odds-(character.getEquipped().getTotalWeight()-enemyNPC.getEquipped().getTotalWeight())+(character.getAttributes().getDexterity()*3)-loss>0){
            damage = ((weapon.getDamage())+(character.getAttributes().getStrength())-places[index].getBlocking());
            if (odds>95){
                System.out.println("Critical hit!");
                damage = damage * 2;
            }
            if (damage<0){
                damage = 0;
            }
            System.out.println("You hit "+enemyNPC.getName()+"'s "+places[index].getLocation()+" for "+damage+" damage with your "+weapon.getName()+".\n");
        }
        else {
            System.out.println("You miss.");
        }
        return damage;
    }

    public static int enemyAttack(Character character, EnemyNPC enemyNPC) throws InterruptedException {
        Armor[] places = new Armor[]{character.getEquipped().getEqHead(),character.getEquipped().getEqBody(),character.getEquipped().getEqGloves(),character.getEquipped().getEqLegs(),character.getEquipped().getEqBoots()};
        int damage = 0;
        Weapon weapon = enemyNPC.getEquipped().getMain();
        if (weapon.getDamage()<enemyNPC.getEquipped().getSecondary().getDamage()){
            weapon = enemyNPC.getEquipped().getSecondary();
        }
        int odds = random.nextInt(150);
        int place = random.nextInt(5);
        System.out.println(enemyNPC.getName()+" swings at your "+places[place].getLocation()+" with their "+weapon.getName()+".");
        Thread.sleep(1000);
        if ((20-weapon.getWeight())+odds-(enemyNPC.getEquipped().getTotalWeight()-character.getEquipped().getTotalWeight())-(character.getAttributes().getDexterity()*5)>0){
            if (weapon.getRange()>5){
                damage = enemyNPC.getBaseDamageLong()+weapon.getDamage()-places[place].getBlocking();
            }
            else {
                damage = enemyNPC.getBaseDamageShort()+weapon.getDamage()-places[place].getBlocking();
            }
            if (odds>95){
                System.out.println("Critical hit!");
                damage = damage * 2;
            }
            if (damage<0){
                damage = 0;
            }
            System.out.println(enemyNPC.getName()+" hit you for "+damage+" damage.\n");
        }
        else {
            System.out.println(enemyNPC.getName()+" misses.\n");
        }

        return damage;
    }

    public static void loot(Character character,EnemyNPC enemyNPC){
        System.out.println("The body has\n(0) "+enemyNPC.getInventory().getMoney()+" dollars and:");
        String[] items = enemyNPC.getEquipped().toString().split("\n",0);
        for (int i = 1;i<items.length+1;i++){
            System.out.println("("+i+")"+items[i-1]);
        }
        Item[] lootable = new Item[]{enemyNPC.getEquipped().getEqHead(),enemyNPC.getEquipped().getEqBody(),enemyNPC.getEquipped().getEqGloves(),enemyNPC.getEquipped().getEqLegs(),enemyNPC.getEquipped().getEqBoots(),enemyNPC.getEquipped().getMain(),enemyNPC.getEquipped().getSecondary(),enemyNPC.getEquipped().getBoot()};
        System.out.println("Enter what you want to take, separated by commas:");
        String[] take = scanner.next().split(",",0);
        for (String item: take){
            if (Merchant.isNumeric(item)){
                int itemNum = Integer.parseInt(item);
                if (itemNum<=8&&itemNum>=0){
                    if (itemNum == 0){
                        System.out.println("You got "+enemyNPC.getInventory().getMoney()+" dollars.");
                        character.getAttributes().getInventory().addMoney(enemyNPC.getInventory().getMoney());
                        System.out.println("You now have "+character.getAttributes().getInventory().getMoney()+" dollars.");
                    }else {
                        System.out.println("You got " + lootable[itemNum - 1]);
                        character.getAttributes().getInventory().addToInventory(lootable[itemNum - 1]);
                    }
                }
            }
        }


    }

    public static void complexAttack(Character character,EnemyNPC enemyNPC,Weapon weapon) throws InterruptedException {
        System.out.println("You can attempt a feint (0), a disarm (1), or a confuse (2):");
        int choice = scanner.nextInt();
        switch (choice){
            case 0:
                if (feintSuccess(character,enemyNPC)){
                    int damage = ((weapon.getDamage())+(character.getAttributes().getStrength())-enemyNPC.getEquipped().getEqBody().getBlocking());
                    System.out.println("You hit "+enemyNPC.getName()+" for "+damage+" damage.");
                    enemyNPC.setHealth(enemyNPC.getHealth()-damage);
                }
                else {
                    character.setHealth(character.getHealth()-enemyAttack(character,enemyNPC));
                }
                break;
        }
    }

    public static boolean feintSuccess(Character character, EnemyNPC enemyNPC){
        int odds = random.nextInt(100);
        odds+=((character.getAttributes().getIntelligence()*5)+(character.getAttributes().getDexterity()*8));
        if(odds>80){
            System.out.println("You tricked "+enemyNPC.getName()+" into blocking early and strike him.");
            return true;
        }else {
            System.out.println(enemyNPC.getName()+" reads your feint and strikes you early.");
            return false;
        }
    }

//    public boolean disarmSuccess(Character character,EnemyNPC enemyNPC){
//
//    }

}