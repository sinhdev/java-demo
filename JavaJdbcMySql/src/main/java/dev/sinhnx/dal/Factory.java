package dev.sinhnx.dal;

import java.util.HashMap;
import java.util.Map;

public class Factory<T> {
    private Map<Class<T>, DAL<T>> registry = new HashMap<>();

    public void registerHandler(Class<T> dataType, DAL<T> handlerType) {
        registry.put(dataType, handlerType);
    }

    // public <T> DAL<T> getHandler(Class<T> clazz) {
    //     return registry.get(clazz).newInstance();
    // }
}