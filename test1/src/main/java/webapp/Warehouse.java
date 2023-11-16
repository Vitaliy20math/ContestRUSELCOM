package webapp;

import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/** Ахкямиев Виталий - Тестовое задание - РУСЭЛКОМ
 *
 * 1) В коде присутствует сортировка, как по дате добавления элемента в список,
 * так и по дате последнего его обновления, в роли обновления может быть
 * либо добавление какого-то количества этого элемента или уменьшение.
 * Фильтры можно наложить любые, в этом проблем нет, но по-скольку не было
 * однозначно сказано по какой именно дате сортировать, сделал на свой выбор

 */
@Data
@Named
@ViewScoped
public class Warehouse implements Serializable {
    private List<Item> items, customItems;
    private Item item;

    //паттерн проверки на то, что число содержит от 1 до 4 цифр
    private static final String NUM = "^\\d{1,4}$";


    public Warehouse() {
        items = new ArrayList<Item>();
        item = new Item();
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }



    /** AddItem()
     * Данный метод является первой инстанцией. Здесь в коллекцию добавляется
     * элемент и далее мы можем работать с данным элементом: увеличивать его количество
     * или же уменьшать. Поставлена проверка на наличие элемента с
     * данным именем в списке, если такое имя уже есть, то необходимо ввести
     * имя отличное от введенного. Если все удовлетворяет условию, то
     * добавляем в список.
     */
    public void addItem() {
        boolean flag = false;
        for (Item e : items) {
            if (item.getName().equals(e.getName())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            items.add(item);
            LocalDateTime momentAdded = LocalDateTime.now();
            item.getMovements().add(new Movement("Добавили", momentAdded, item.getCount()));
            item = new Item(); // Сброс значений объекта item
        }
    }

    /** addQuantityForItem(Item item, Integer quantity)
     *  В данном методе увеличиваем количество элемента, также стоит валидатор
     *  с паттерном на проверку передаваемого значения, т.к важно, чтобы приходило
     *  число. ДЛЯ ПРИМЕРА все входные параметры в программе ограничены четвертым
     *  порядком (10^4), а именно, максимальным число может быть 9999.
     * @param item
     * @param quantity
     */
    public void addQuantityForItem(Item item, Integer quantity) {
        if (quantity != null && quantity.toString().matches(NUM)) {
            for (Item it : items) {
                if (it.getName().equals(item.getName())) {
                    item.setCount(it.getCount() + quantity);
                    LocalDateTime updateTime = LocalDateTime.now();
                    item.getMovements().add(new Movement("Добавили", updateTime, quantity));
                    break;
                }
            }
        }
    }

    /**
     * Сортировка по имени
     */
    public void sortItemsByName() {
        items.sort(Comparator.comparing(Item::getName));
    }

    /**
     * Сортировка по дате добавления.
     */
    public void sortItemsByDateAdded() {
        items.sort((item1, item2) -> {
            LocalDateTime dateTime1 = getMovementDateAdded(item1);
            LocalDateTime dateTime2 = getMovementDateAdded(item2);
            return dateTime1.compareTo(dateTime2);
        });
    }

    private LocalDateTime getMovementDateAdded(Item item) {
        List<Movement> movements = item.getMovements();
        if (movements.isEmpty()) {
            return LocalDateTime.MIN;
        } else {
            Movement latestMovement = movements.get(0);
            return latestMovement.getDate();
        }
    }
    /**
     * Сортировка по дате последнего обновления
     */
    public void sortItemsByDateUpdate() {
        items.sort((item1, item2) -> {
            LocalDateTime dateTime1 = getMovementDateUpdate(item1);
            LocalDateTime dateTime2 = getMovementDateUpdate(item2);
            return dateTime2.compareTo(dateTime1);
        });
    }

    private LocalDateTime getMovementDateUpdate(Item item) {
        List<Movement> movements = item.getMovements();
        if (movements.isEmpty()) {
            return LocalDateTime.MIN;
        } else {
            Movement latestMovement = movements.get(movements.size() - 1);
            return latestMovement.getDate();
        }
    }
    /**
     * Удаление элемента из списка
     */
    public void deleteAll(Item item) {
        customItems = items.stream().filter(e -> e != item).collect(Collectors.toList());
        items = customItems;
        //item.getMovements().add(new Movement("DOWN", LocalDateTime.now(), item.getCount()));
    }

    /**
     * Сокращаем количество элемента, также поставлен валидатор, программа
     * будет бездействовать если в поле ввода будут вводиться некорректные значения
     */
    public void decreaseItemCount(Item item, Integer quantity) {
        if (quantity != null && quantity.toString().matches(NUM)) {
            for (Item e : items) {
                if (e.equals(item)) {
                    LocalDateTime momentDec = LocalDateTime.now();
                    item.setCount(item.getCount() - quantity);
                    item.getMovements().add(new Movement("Убрали", momentDec, quantity));
                    break; // необходимо прервать цикл, чтобы не добавить элемент item в customItems
                }
            }
        }
    }

}