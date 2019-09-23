package com.rio.proj.index.stage;

import com.rio.proj.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class StageIndex implements IndexAware<Long,StageObject> {

    private static Map<Long, StageObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public StageObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, StageObject value) {
        log.info("before add: {}",objectMap);
        objectMap.put(key,value);
        log.info("after add: {}",objectMap);
    }

    @Override
    public void update(Long key, StageObject value) {
        log.info("before update: {}",objectMap);
        StageObject oldObject = objectMap.get(key);
        if(null == oldObject){
            objectMap.put(key,value);
        } else {
            oldObject.update(value);
        }
        log.info("after update: {}",objectMap);
    }

    @Override
    public void delete(Long key, StageObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}",objectMap);
    }
}
