package de.flaviait.codemeetup.order;

public class OrderEntity {

    private Long id;

    private String name;

    private DrinkType drink;

    public OrderEntity() {
    }

    public OrderEntity(String name, DrinkType drink) {
        this.name = name;
        this.drink = drink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrinkType getDrink() {
        return drink;
    }

    public void setDrink(DrinkType drink) {
        this.drink = drink;
    }
}
