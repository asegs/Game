public class Armor extends Item{
    private int blocking;
    private String location;

    public Armor(int value, int weight, String name,int blocking,String location) {
        super(value, weight, name);
        this.blocking = blocking;
        this.location = location;
    }

    public int getBlocking() {
        return blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }


    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name="+this.getName()+
                ", value="+this.getValue()+
                ",weight="+this.getWeight()+
                ", blocking=" + blocking +
                ", location='" + location + '\'' +
                '}';
    }

    public String toFile(){
        return this.getName()+","+this.getBlocking()+","+this.getWeight()+","+this.getValue()+","+this.location;
    }
}
