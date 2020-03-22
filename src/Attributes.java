public class Attributes {
    private int strength;
    private int charisma;
    private int constitution;
    private int intelligence;
    private int dexterity;
    private int luck;

    private int morality;
    private String name;
    private int age;
    private int height;
    private int weight;

    private int level;
    private int experience;
    private int difficulty;

    private Inventory inventory;

    public Attributes() {
        strength = 0;
        charisma = 0;
        constitution = 0;
        intelligence = 0;
        dexterity = 0;
        luck = 0;
        name = "anon";
        age = 0;
        height = 0;
        weight = 0;
        level = 1;
        experience = 0;
        morality = 0;
        inventory = new Inventory();

    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getMorality() {
        return morality;
    }

    public void setMorality(int morality) {
        this.morality = morality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public int getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(int n){
        difficulty = n;
    }
}
