import java.util.Random;

public class Weapon extends Item {
    private int damage;
    private int range;
    private boolean twoHanded;
    private String type;

    public Weapon(int value, int weight, String name,int damage,int range,boolean twoHanded,String type) {
        super(value, weight, name);
        this.damage = damage;
        this.range = range;
        this.twoHanded = twoHanded;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" +this.getName()+
                ", value="+this.getValue()+
                ", weight="+this.getWeight()+
                ", damage=" + damage +
                ", range=" + range +
                ", twoHanded=" + twoHanded +
                ", type='" + type + '\'' +
                '}';
    }

    public String toFile(){
        return this.getName()+","+damage+","+this.getWeight()+","+this.getValue()+","+range+","+type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setTwoHanded(boolean hands){
        twoHanded = hands;
    }


}
