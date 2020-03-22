import java.util.Random;

public class EnemyNPC {
    Random random = new Random();
    private Inventory inventory;
    private int health;
    private int maxHealth;
    private int baseDamageShort;
    private int baseDamageLong;
    private String name;
    private Equipped equipped;

    public EnemyNPC(){
        inventory = new Inventory();
        health = 0;
        maxHealth = 0;
        baseDamageShort = 0;
        baseDamageLong = 0;
        name = "empty";
        equipped = new Equipped();
    }

    public String nameNPC(){
        String[] firstNames = new String[]{"Keith","Ogdan","Summel","Hepsid"};
        String[] separators = new String[]{"Of","The","La"};
        String[] lastNames = new String[]{"Doom","Mourning","Execution","Wary"};
        return firstNames[random.nextInt(firstNames.length)]+" "+separators[random.nextInt(separators.length)]+" "+lastNames[random.nextInt(lastNames.length)];
    }

    public EnemyNPC randomNPC(Character character){
        EnemyNPC npc = new EnemyNPC();
        npc.setName(nameNPC());
        npc.equipped.setEqHead(RandomItems.randomArmor("Head",-1));
        npc.equipped.setEqBody(RandomItems.randomArmor("Body",-1));
        npc.equipped.setEqGloves(RandomItems.randomArmor("Gloves",-1));
        npc.equipped.setEqLegs(RandomItems.randomArmor("Legs",-1));
        npc.equipped.setEqBoots(RandomItems.randomArmor("Boots",-1));
        npc.equipped.setMain(RandomItems.randomWeapon(-1));
        npc.equipped.setSecondary(RandomItems.randomWeapon(-1));
        npc.equipped.setBoot(RandomItems.randomWeapon(-1));
        npc.setMaxHealth((character.getAttributes().getDifficulty()+1)*5+(random.nextInt(3)*character.getAttributes().getLevel()));
        npc.setHealth(npc.getMaxHealth());
        if (npc.getEquipped().getMain().getRange()>5){
            npc.baseDamageLong = 4*(character.getAttributes().getDifficulty()+1);
            npc.baseDamageShort = 2*(character.getAttributes().getDifficulty()+1);
        }
        else {
            npc.baseDamageLong = 2*(character.getAttributes().getDifficulty()+1);
            npc.baseDamageShort = 4*(character.getAttributes().getDifficulty()+1);
        }
        npc.inventory.addMoney(random.nextInt(maxHealth*character.getAttributes().getLuck())+1);
        return npc;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public int getBaseDamageShort() {
        return baseDamageShort;
    }

    public void setBaseDamageShort(int baseDamageShort) {
        this.baseDamageShort = baseDamageShort;
    }

    public int getBaseDamageLong() {
        return baseDamageLong;
    }

    public void setBaseDamageLong(int baseDamageLong) {
        this.baseDamageLong = baseDamageLong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipped(Equipped equipped){
        this.equipped = equipped;
    }

    public Equipped getEquipped(){
        return equipped;
    }
}
