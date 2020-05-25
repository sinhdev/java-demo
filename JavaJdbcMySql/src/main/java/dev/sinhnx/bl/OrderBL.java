package dev.sinhnx.bl;

import dev.sinhnx.dal.OrderDAL;
import dev.sinhnx.persitance.Order;

public class OrderBL {
    private OrderDAL orderDAL = new OrderDAL();

    public boolean insertOrder(Order order) {
        return orderDAL.insert(order) > 0;
    }
}