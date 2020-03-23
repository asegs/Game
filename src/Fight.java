import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public void duel(Character character,EnemyNPC enemyNPC){
        while (character.getHealth()>0&&enemyNPC.getHealth()>0){
            System.out.println(character.getAttributes().getName()+"'s turn.");
        }
    }

    public static void attackEnemy(Character character,EnemyNPC enemyNPC){
        Armor[] places = new Armor[]{enemyNPC.getEquipped().getEqHead(),enemyNPC.getEquipped().getEqBody(),enemyNPC.getEquipped().getEqGloves(),enemyNPC.getEquipped().getEqLegs(),enemyNPC.getEquipped().getEqBoots()};
        System.out.println("(0) "+character.getEquipped().getMain().toString()+"\n(1) "+character.getEquipped().getSecondary().toString()+"\n(2) "+character.getEquipped().getBoot().toString());
        System.out.println("Enter the number of your attack weapon:");
        Weapon weapon;
        int attackNum = scanner.nextInt();
        if (attackNum==1){
            weapon = character.getEquipped().getSecondary();
        }
        if (attackNum==2){
            weapon = character.getEquipped().getBoot();
        }
        else {
            weapon = character.getEquipped().getMain();
        }
        System.out.println("Do you want to attack the head (0), the body (1), the hands (2), the legs (3), the feet (4), or attempt a complex maneuver (5):");
        int attack = scanner.nextInt();
        int odds = random.nextInt(100);
        int damage = 0;
        switch (attack){
            case 0:
                damage = attackAttempt(character,enemyNPC,20,"head",0,odds,weapon,places);
                break;
            case 1:
                damage = attackAttempt(character,enemyNPC,0,"body",1,odds,weapon,places);
                break;
            case 2:
                damage = attackAttempt(character,enemyNPC,25,"hands",2,odds,weapon,places);
                break;
            case 3:
                damage = attackAttempt(character,enemyNPC,0,"legs",3,odds,weapon,places);
                break;
            case 4:
                damage = attackAttempt(character,enemyNPC,15,"feet",4,odds,weapon,places);
        }

    }
    public static int attackAttempt(Character character, EnemyNPC enemyNPC, int loss, String place, int index, int odds, Weapon weapon, Armor[] places){
        int damage = 0;
//        System.out.println(odds);
//        System.out.println(character.getEquipped().getTotalWeight()+"\n"+enemyNPC.getEquipped().getTotalWeight());
//        System.out.println(odds-(character.getEquipped().getTotalWeight()-enemyNPC.getEquipped().getTotalWeight())+(character.getAttributes().getDexterity()*3)-loss);
        if (odds-(character.getEquipped().getTotalWeight()-enemyNPC.getEquipped().getTotalWeight())+(character.getAttributes().getDexterity()*3)-loss>0){
            damage = ((weapon.getDamage())+(character.getAttributes().getStrength())-places[index].getBlocking());
            if (odds>95){
                System.out.println("Critical hit!");
                damage = damage * 2;
            }
            System.out.println("You hit "+enemyNPC.getName()+"'s "+place+" for "+damage+" damage.");
        }
        else {
            System.out.println("You miss.");
        }
        return damage;
    }
}
