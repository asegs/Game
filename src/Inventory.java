import java.util.ArrayList;

public class Inventory {
    private int money;
    private ArrayList<Item> items;

    public Inventory(){
        money = 0;
        items = new ArrayList<>();

    }

    public int getMoney() {
        return money;
    }

    public void spendMoney(int n){
        if (money-n<0){
            //System.out.println("You cannot afford that.");
        }else{
            money-=n;
            //System.out.println("You have "+money+" dollars remaining.");
        }
    }

    public void addMoney(int n){
        money+=n;
        //System.out.println("You now have "+money+" dollars remaining.");
    }

    public void addToInventory(Item e){
        items.add(e);
        //System.out.println("You now have the item: "+e.getName());
    }

    public void removeFromInventory(Item e){
        items.remove(e);
        //System.out.println("You lose the item "+e.getName());
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> getArmors(){
        ArrayList<Item> armorList = new ArrayList<>();
        for (Item item:items){
            if (item instanceof Armor){
                armorList.add((Armor) item);
            }
        }
        return armorList;
    }

    public ArrayList<Item> getWeapons(){
        ArrayList<Item> weaponList = new ArrayList<>();
        for (Item item:items){
            if (item instanceof Weapon){
                weaponList.add((Weapon) item);
            }
        }
        return weaponList;
    }

    public ArrayList<Item> getPotions(){
        ArrayList<Item> potionList = new ArrayList<>();
        for (Item item:items){
            if (item instanceof Potion){
                potionList.add((Potion)item);
            }
        }
        return potionList;
    }

    public void setMoney(int n){
        money=n;
    }

    public ArrayList<Item> getEquipment(){
        ArrayList<Item> equipmentList = new ArrayList<>();
        for (Item item:items){
            if (item instanceof Equipment){
                equipmentList.add((Equipment) item);
            }
        }
        return equipmentList;
    }
}
