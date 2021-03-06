public abstract class Item {
    private int value;
    private int weight;
    private String name;

    public Item(int value,int weight,String name){
        this.value=value;
        this.weight=weight;
        this.name=name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
