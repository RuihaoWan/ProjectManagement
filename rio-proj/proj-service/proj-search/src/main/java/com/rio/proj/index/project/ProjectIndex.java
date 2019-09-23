package com.rio.proj.index.project;

import com.rio.proj.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ProjectIndex implements IndexAware<Long,ProjectObject>{

    private static Map<Long,ProjectObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public ProjectObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, ProjectObject value) {
        log.info("before add: {}",objectMap);
        objectMap.put(key,value);
        log.info("after add: {}",objectMap);
    }

    @Override
    public void update(Long key, ProjectObject value) {
        log.info("before update: {}",objectMap);
        ProjectObject oldObject = objectMap.get(key);
        if(oldObject == null){
            objectMap.put(key,value);
        }else{
            oldObject.update(value);
        }
        log.info("after update: {}",objectMap);
    }

    @Override
    public void delete(Long key, ProjectObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}",objectMap);
    }
}
