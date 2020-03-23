public class Equipped {
    private Armor eqHead;
    private Armor eqBody;
    private Armor eqGloves;
    private Armor eqLegs;
    private Armor eqBoots;
    private Weapon main;
    private Weapon secondary;
    private Weapon boot;

    public Equipped(){
        eqHead = new Armor(0,0,"bowl cut",0,"Head");
        eqBody = new Armor(0,0,"bare chested",0,"Body");
        eqGloves = new Armor(0,0,"bare hands",0,"Gloves");
        eqLegs = new Armor(0,0,"bare legs",0,"Legs");
        eqBoots = new Armor(0,0,"barefoot", 0,"Boots");
        main = new Weapon(0,0,"right punch",1,1,false,"Crush");
        secondary = new Weapon(0,0,"left punch",1,1,false,"Crush");
        boot = new Weapon(0,0,"shiv",1,1,false,"Stab");
    }

    public Armor getEqHead() {
        return eqHead;
    }

    public void setEqHead(Armor eqHead) {
        this.eqHead = eqHead;
    }

    public Armor getEqBody() {
        return eqBody;
    }

    public void setEqBody(Armor eqBody) {
        this.eqBody = eqBody;
    }

    public Armor getEqGloves() {
        return eqGloves;
    }

    public void setEqGloves(Armor eqGloves) {
        this.eqGloves = eqGloves;
    }

    public Armor getEqLegs() {
        return eqLegs;
    }

    public void setEqLegs(Armor eqLegs) {
        this.eqLegs = eqLegs;
    }

    public Armor getEqBoots() {
        return eqBoots;
    }

    public void setEqBoots(Armor eqBoots) {
        this.eqBoots = eqBoots;
    }

    public Weapon getMain() {
        return main;
    }

    public void setMain(Weapon main) {
        this.main = main;
    }

    public Weapon getSecondary() {
        return secondary;
    }

    public void setSecondary(Weapon secondary) {
        this.secondary = secondary;
    }

    public Weapon getBoot() {
        return boot;
    }

    public void setBoot(Weapon boot) {
        this.boot = boot;
    }

    public int getTotalWeight(){
        return (getEqHead().getWeight()+getEqBody().getWeight()+getEqGloves().getWeight()+getEqLegs().getWeight()+getEqBoots().getWeight()+getMain().getWeight()+getSecondary().getWeight()+getBoot().getWeight());
    }
}
