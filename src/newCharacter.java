import java.util.Scanner;

public class newCharacter {
    public Attributes buildCharacter(){
        Attributes attributes = new Attributes();
        System.out.println("Here you will build a character.  You will be able to change any field once finished.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is this character's name?:");
        attributes.setName(scanner.nextLine());
        attributes.setAge(GetCappedInt.capped("What is this character's age?:",13,85));
        attributes.setWeight(GetCappedInt.capped("What is this character's weight?:",40,450));
        attributes.setHeight(GetCappedInt.capped("What is this character's height in inches?:",42,90));
        int abilityPoints = 6;
        while (abilityPoints>0){
            System.out.println("You have "+abilityPoints+" ability points left to assign.");
            System.out.println("The attributes are:\n1. Strength ("+attributes.getStrength()+")\n2. Charisma ("+attributes.getCharisma()+")\n3. Constitution ("+attributes.getConstitution()+")\n4. Intelligence ("+attributes.getIntelligence()+")\n5. Dexterity ("+attributes.getDexterity()+")\n6. Luck ("+attributes.getLuck()+")");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    attributes.setStrength(attributes.getStrength()+1);
                    break;
                case 2:
                    attributes.setCharisma(attributes.getCharisma()+1);
                    break;
                case 3:
                    attributes.setConstitution(attributes.getConstitution()+1);
                    break;
                case 4:
                    attributes.setIntelligence(attributes.getIntelligence()+1);
                    break;
                case 5:
                    attributes.setDexterity(attributes.getDexterity()+1);
                    break;
                case 6:
                    attributes.setLuck(attributes.getLuck()+1);
                    break;
            }
            abilityPoints-=1;
        }
        return attributes;
    }

    public String characterCSV(Attributes attributes){
        String stringify = attributes.getName()+","+attributes.getLevel()+","+attributes.getExperience()+","+attributes.getAge()+","+attributes.getWeight()+","+attributes.getHeight()+","+attributes.getMorality()+","+attributes.getStrength()+","+attributes.getCharisma()+","+attributes.getConstitution()+","+attributes.getIntelligence()+","+attributes.getDexterity()+","+attributes.getLuck()+","+attributes.getInventory().getMoney()+"\n";
        for (Item item : attributes.getInventory().getItems()){
            if (item instanceof Weapon){
                Weapon weapon = (Weapon)item;
                stringify+="<I>"+weapon.toString();
            }
            if (item instanceof Armor){
                Armor armor = (Armor)item;
                stringify+="<I>"+armor.toString();
            }
        }
        return stringify;
    }

    public void writeCharacter(String string){
        String name = string.split(",",0)[0];
        fileEditor.replaceFile("src/Characters/char_"+name+".txt",string,false);
    }

//    public static void main(String[] args) {
//        newCharacter newCharacter1 = new newCharacter();
//        newCharacter1.writeCharacter(newCharacter1.characterCSV(newCharacter1.buildCharacter()));
//    }
}
