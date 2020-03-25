import javax.sql.rowset.spi.SyncResolver;
import java.util.HashMap;
import java.util.Scanner;

public class Smithing {
    Scanner scanner = new Scanner(System.in);
    public Weapon upgradeWeapon(Weapon weapon){
        HashMap<String,Integer> minWeights = new HashMap<>();
        minWeights.put("Concealed",1);
        minWeights.put("Light",2);
        minWeights.put("Standard",3);
        minWeights.put("Great",5);
        int repairCost = 0;
        System.out.println("Increase damage (0) or reduce weight (1):");
        int choice = scanner.nextInt();
        switch (choice){
            case 0:
                System.out.println("Current weapon damage is "+weapon.getDamage()+".  How many points would you like to increase it by?:");
                int increase = scanner.nextInt();
                repairCost = (weapon.getDamage()+increase)*40;
                for (int i = 10;i<100;i+=10){
                    if (weapon.getDamage()+increase>i){
                        repairCost = repairCost * 2;
                    }
                }
                System.out.println("This upgrade will cost "+repairCost+" dollars.  Accept Y/N:");
                String entry = scanner.next();
                if (entry.equals("Y")){
                    weapon = rename(weapon);
                    weapon.setDamage(weapon.getDamage()+increase);
                    weapon.setValue(repairCost);
                    return weapon;
                }
                break;
            case 1:
                int minWeight = minWeights.get(weapon.getName().split(" ",0)[0]);
                System.out.println("Your "+weapon.getName()+"'s minimum weight is "+minWeight+".");
                System.out.println("Current weapon weight is "+weapon.getWeight()+".  How many points would you like to decrease it by?:");
                int reduction = scanner.nextInt();
                if(weapon.getWeight()-reduction<minWeight){
                    System.out.println("That is too light.  Setting minimum weapon weight.");
                    reduction = weapon.getWeight()-minWeight;
                }
                int reduceWeightCost = (int) (weapon.getValue() / 2);
                reduceWeightCost = reduceWeightCost * reduction;
                if (weapon.getWeight()-reduction == minWeight){
                    reduceWeightCost = reduceWeightCost * 3;
                }
                System.out.println("This improvement will set the weight to "+(weapon.getWeight()-reduction)+" and cost "+reduceWeightCost+" dollars.  Accept Y/N:");
                String reduceEntry = scanner.next();
                if (reduceEntry.equals("Y")){
                    weapon = rename(weapon);
                    weapon.setWeight(weapon.getWeight()-reduction);
                    weapon.setValue(reduceWeightCost);
                    return weapon;
                }

        }
        return weapon;
    }

    public Weapon rename(Weapon weapon){
        //TODO
        System.out.println("Do you want to rename your weapon? Current name is: "+weapon.getName()+" Y/N:");
        String nameEntry = scanner.next();
        if (nameEntry.equals("Y")){
            System.out.println("What is your new weapon name?:");
            weapon.setName(scanner.nextLine());
        }
        return weapon;
    }
}
