public class Equipment extends Item{
    private String relevantSkill;
    private int bonus;
    public Equipment(int value, int weight, String name,String relevantSkill,int bonus) {
        super(value, weight, name);
        this.relevantSkill = relevantSkill;
        this.bonus = bonus;
    }

    public String getRelevantSkill() {
        return relevantSkill;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String toFile(){
        return this.getName()+","+this.relevantSkill+","+this.getWeight()+","+this.getValue()+","+this.bonus;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name="+getName()+
                ", value="+getValue()+
                ", weight="+getWeight()+
                ", relevantSkill='" + relevantSkill + '\'' +
                ", bonus=" + bonus +
                '}';
    }
}
