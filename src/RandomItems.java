import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class RandomItems {
    public static int getValFile(int pos, double mod, String[] weaponAtts){
        return (int)(Integer.parseInt(weaponAtts[pos])*mod);
    }

    public static Weapon makeWeapon(String prefix, double dmgMod, double wgtMod, double valMod, double rangeMod, String[] weaponAtts, boolean twoHanded){
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

    public static Weapon randomWeapon (int preSize){
        Random random = new Random();
        String file = fileReader.reader("src/Base Equipment/base_weapons.txt","\n");
        String[] weapons = file.split("\n",0);
        int size = random.nextInt(4);
        String weapon = weapons[random.nextInt(weapons.length)];
        String[] weaponAtts = weapon.split(",",0);
        Weapon weapon1 =  new Weapon(0,0,"empty",0,0,false,"empty");
        if (size>=0&&size<4){
            size = preSize;
        }
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
        return weapon1;
    }

    public static Armor makeArmor(String prefix,double defMod,double wgtMod,double valMod,String placement,String[] armorAtts){
        String name = prefix+armorAtts[0];
        int def = getValFile(1,defMod,armorAtts);
        int weight = getValFile(2,wgtMod,armorAtts);
        int value = getValFile(3,valMod,armorAtts);
        return new Armor(value,weight,name,def,placement);
    }

    public static Armor randomArmor(String place,int weight){
        HashSet<String> places = new HashSet<>(Arrays.asList("Head","Body","Gloves","Legs","Boots"));
        Random random = new Random();
        String file = fileReader.reader("src/Base Equipment/base_armors.txt","\n");
        String[] armors = file.split("\n",0);
            int material = random.nextInt(4);
            if (weight>=0&&weight<4){
                material = weight;
            }
            String armor = armors[random.nextInt(armors.length)];
            String[] armorAtts = armor.split(",",0);
            if (places.contains(place)){
                armorAtts[4] = place;
            }
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
        return armor1;
    }
}
