package com.rio.proj.index.user;

import com.rio.proj.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UserIndex implements IndexAware<Long,UserObject> {
    private static Map<Long,UserObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public UserObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, UserObject value) {
        log.info("before add: {}",objectMap);
        objectMap.put(key,value);
        log.info("after add: {}",objectMap);
    }

    @Override
    public void update(Long key, UserObject value) {
        log.info("before update: {}",objectMap);
        UserObject oldObject = objectMap.get(key);
        if (oldObject == null) {
            objectMap.put(key,value);
        } else {
            oldObject.update(value);
        }
        log.info("after update: {}",objectMap);
    }

    @Override
    public void delete(Long key, UserObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}",objectMap);
    }
}
