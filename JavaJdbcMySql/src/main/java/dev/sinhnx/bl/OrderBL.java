package dev.sinhnx.bl;

import dev.sinhnx.dal.DAL;
import dev.sinhnx.dal.DalFactory;
import dev.sinhnx.persitance.Order;

public class OrderBL {
    private DAL<Order> orderDAL = DalFactory.getDAL(Order.class);

    public boolean insertOrder(Order order) {
        return orderDAL.insert(order) > 0;
    }
}