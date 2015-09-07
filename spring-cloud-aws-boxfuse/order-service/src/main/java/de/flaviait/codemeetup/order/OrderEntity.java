package de.flaviait.codemeetup.order;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "order_orders")
@SequenceGenerator(name = "order_generator", sequenceName = "seq_order")
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "order_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "drink")
    @Enumerated(EnumType.STRING)
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
