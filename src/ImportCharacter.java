public class ImportCharacter {
    public static Character impChar(String filename){
        String charString = fileReader.reader(filename,"\n");
        Character character = new Character();
        Attributes attributes = new Attributes();
        Inventory inventory = new Inventory();
        String[] imported = charString.split("\n",0);
        for (String string : imported){
            if (string.substring(0,1).equals("<")){
                String type = string.substring(0,3);
                switch (type){
                    case "<E>":
                        String armorString = string.substring(3,string.length());
                        String[] armorAtts = armorString.split(",",0);
                        Armor armor = new Armor(Integer.parseInt(armorAtts[3]),Integer.parseInt(armorAtts[2]),armorAtts[0],Integer.parseInt(armorAtts[1]),armorAtts[4]);
                        switch (armor.getLocation()){
                            case "Head":
                                character.getEquipped().setEqHead(armor);
                                break;
                            case "Body":
                                character.getEquipped().setEqBody(armor);
                                break;
                            case "Gloves":
                                character.getEquipped().setEqGloves(armor);
                                break;
                            case "Legs":
                                character.getEquipped().setEqLegs(armor);
                                break;
                            case "Boots":
                                character.getEquipped().setEqBoots(armor);
                                break;
                        }
                        break;
                    case "<1>" :
                        String weaponString = string.substring(3,string.length());
                        character.getEquipped().setMain(weaponFromFile(weaponString));
                        break;
                    case "<2>":
                        String weaponString1 = string.substring(3,string.length());
                        character.getEquipped().setSecondary(weaponFromFile(weaponString1));
                        break;
                    case "<3>":
                        String weaponString2 = string.substring(3,string.length());
                        character.getEquipped().setBoot(weaponFromFile(weaponString2));
                        break;
                    case "<P>":
                        String potionString = string.substring(3,string.length());
                        String[] potionAtts = potionString.split(",",0);
                        Potion potion = new Potion(Integer.parseInt(potionAtts[1]),Integer.parseInt(potionAtts[2]),potionAtts[0],potionAtts[3],Integer.parseInt(potionAtts[4]),potionAtts[5],Integer.parseInt(potionAtts[6]));
                        inventory.addToInventory(potion);
                        break;
                    case "<T>":
                        String toolString = string.substring(3,string.length());
                        String[] toolAtts = toolString.split(",",0);
                        Equipment equipment = new Equipment(Integer.parseInt(toolAtts[3]),Integer.parseInt(toolAtts[2]),toolAtts[0],toolAtts[1],Integer.parseInt(toolAtts[4]));
                        inventory.addToInventory(equipment);
                        break;
                    case "<A>":
                        String armorString1 = string.substring(3,string.length());
                        String[] armorAtts1 = armorString1.split(",",0);
                        Armor armor1 = new Armor(Integer.parseInt(armorAtts1[3]),Integer.parseInt(armorAtts1[2]),armorAtts1[0],Integer.parseInt(armorAtts1[1]),armorAtts1[4]);
                        inventory.addToInventory(armor1);
                        break;
                    case "<W>":
                        String weaponString3 = string.substring(3,string.length());
                        inventory.addToInventory(weaponFromFile(weaponString3));
                        break;

                }
            }
            else{
                String[] spliced = string.split(",",0);
                attributes.setName(spliced[0]);
                attributes.setLevel(Integer.parseInt(spliced[1]));
                attributes.setExperience(Integer.parseInt(spliced[2]));
                attributes.setAge(Integer.parseInt(spliced[3]));
                attributes.setWeight(Integer.parseInt(spliced[4]));
                attributes.setHeight(Integer.parseInt(spliced[5]));
                attributes.setMorality(Integer.parseInt(spliced[6]));
                attributes.setStrength(Integer.parseInt(spliced[7]));
                attributes.setCharisma(Integer.parseInt(spliced[8]));
                attributes.setConstitution(Integer.parseInt(spliced[9]));
                attributes.setIntelligence(Integer.parseInt(spliced[10]));
                attributes.setDexterity(Integer.parseInt(spliced[11]));
                attributes.setLuck(Integer.parseInt(spliced[12]));
                inventory.setMoney(Integer.parseInt(spliced[13]));
                character.setHealth(Integer.parseInt(spliced[14]));
                character.setMaxHealth(Integer.parseInt(spliced[15]));
                attributes.setDifficulty(Integer.parseInt(spliced[16]));
            }
        }
        attributes.setInventory(inventory);
        character.setAttributes(attributes);
        return character;
    }

    public static Weapon weaponFromFile(String string){
        String[] weaponAtts = string.split(",",0);
        Weapon weapon = new Weapon((Integer.parseInt(weaponAtts[3])),Integer.parseInt(weaponAtts[2]),weaponAtts[0],Integer.parseInt(weaponAtts[1]),Integer.parseInt(weaponAtts[4]),false,weaponAtts[5]);
        String name = weapon.getName();
        String[] chunked = name.split(" ",0);
        if (chunked[0].equals("Great")||chunked[0].equals("Standard")){
            weapon.setTwoHanded(true);
        }
        return weapon;
    }
}
