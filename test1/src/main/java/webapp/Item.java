package webapp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Item {
    private String name;
    private int count;
    private Integer quantityToAdd;

    private Integer quantityToDec;

    private List<Movement> movements = new ArrayList<>();
    //private List<Movement> movements = new ArrayList<Movement>();
    public Item() {
    }
    public Item(String name, int i) {
        this.name = name;
        this.count = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public Integer getQuantityToAdd() {
        return quantityToAdd;
    }

    public void setQuantityToAdd(Integer quantityToAdd) {
        this.quantityToAdd = quantityToAdd;
    }

    public Integer getQuantityToDec() {
        return quantityToDec;
    }

    public void setQuantityToDec(Integer quantityToDec) {
        this.quantityToDec = quantityToDec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return count == item.count && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
