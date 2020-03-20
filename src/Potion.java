public class Potion extends Item{
    private String attributeEffected;
    private int duration;
    private String color;
    private int change;
    public Potion(int value, int weight, String name,String attributeEffected,int duration,String color,int change) {
        super(value, weight, name);
        this.attributeEffected = attributeEffected;
        this.duration = duration;
        this.color = color;
        this.change = change;
    }


    public String getAttributeEffected() {
        return attributeEffected;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String toFile(){
        return this.getName()+","+this.getValue()+","+this.getWeight()+","+attributeEffected+","+duration+","+color+","+change;
    }

    @Override
    public String toString() {
        return "Potion{" +
                "name="+this.getName()+
                ", value="+this.getValue()+
                ", weight="+this.getWeight()+
                ", attributeEffected='" + attributeEffected + '\'' +
                ", duration=" + duration +
                ", color='" + color + '\'' +
                ", change=" + change +
                '}';
    }
}
