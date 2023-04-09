package com.warehouse.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends AbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private Integer count;

    @Override
    public String toString() {
        return "OrderItem{" +
                ", product=" + product.getName() +
                ", count=" + count +
                '}';
    }
}
