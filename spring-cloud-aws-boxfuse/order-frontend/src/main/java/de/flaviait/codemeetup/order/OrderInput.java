package de.flaviait.codemeetup.order;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class OrderInput {

    private String name;

    private DrinkType drink;

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

    public OrderEntity asEntity() {
        return new OrderEntity(name, drink);
    }

    public void validate() {
        if (name == null) {
            throw new RuntimeException("name must not be null");
        }

        if (drink == null) {
            throw new RuntimeException("drink must not be null");
        }
    }
}
