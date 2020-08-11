package dev.sinhnx.threelayer.dal;

import dev.sinhnx.threelayer.persistance.*;

public class DalFactory {
    private enum ValidDal {

        Customer {
            @Override
            DAL<Customer> make() {
                return new CustomerDAL();
            }
        },
        Item {
            @Override
            DAL<Item> make() {
                return new ItemDAL();
            }
        },
        Order {
            @Override
            DAL<Order> make() {
                return new OrderDAL();
            }
        };

        abstract <T> DAL<T> make();
    }

    public static <T> DAL<T> getDAL(Class<T> classType) {
        if (classType == Customer.class) {
            return ValidDal.Customer.make();
        } else if (classType == Item.class) {
            return ValidDal.Item.make();
        } else if (classType == Order.class) {
            return ValidDal.Order.make();
        }
        return null;
    }
}