package com.warehouse.work.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderElements = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "orderStatus_id")
    private OrderStatus status;

    private Date dateOrder;

    private Date dateDelivery;

    @ManyToOne
    @JoinColumn(name = "transporter_id")
    private Transporter transporter;

    @Override
    public String toString() {
        return "Order [creator=" + creator.getUsername() + ", status=" + status + ", dateOrder="
                + dateOrder + ", dateDelivery=" + dateDelivery + ", transporter=" + transporter.getName() + "]";
    }

}