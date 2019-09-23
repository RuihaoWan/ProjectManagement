package com.rio.proj.index.type;

import com.rio.proj.index.IndexAware;
import com.rio.proj.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class TaskTypeIndex implements IndexAware<Long, Set<String>> {

    private static Map<Long,Set<String>> taskTypeMap;
    private static Map<String,Set<Long>> typeTaskMap;

    static {
        taskTypeMap = new ConcurrentHashMap<>();
        typeTaskMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<String> get(Long key) {
        return taskTypeMap.get(key);
    }

    @Override
    public void add(Long key, Set<String> value) {
        log.info("TaskTypeMap, before add: {}", taskTypeMap);
        Set<String> typeString = CommonUtils.getOrCreate(
                key, taskTypeMap ,
                ConcurrentSkipListSet::new
        );
        typeString.addAll(value);
        for (String type : value) {
            Set<Long> tasks = CommonUtils.getOrCreate(
                    type,typeTaskMap,ConcurrentSkipListSet::new
            );
            tasks.add(key);
        }
        log.info("TaskTypeMap, after add: {}", taskTypeMap);
    }

    @Override
    public void update(Long key, Set<String> value) {
        log.info("TaskType Index Not support update");
    }

    @Override
    public void delete(Long key, Set<String> value) {
        log.info("TaskTypeMap, before delete: {}", taskTypeMap);
        Set<String> typeString = CommonUtils.getOrCreate(
                key, taskTypeMap ,
                ConcurrentSkipListSet::new
        );
        typeString.removeAll(value);
        for (String type : value) {
            Set<Long> tasks = CommonUtils.getOrCreate(
                    type,typeTaskMap,ConcurrentSkipListSet::new
            );
            tasks.remove(key);
        }
        log.info("TaskTypeMap, after delete: {}", taskTypeMap);
    }

    public boolean match(Long taskId, List<String> types) {
        if (taskTypeMap.containsKey(taskId)
            && CollectionUtils.isNotEmpty(taskTypeMap.get(taskId))) {
            Set<String> typeSet = taskTypeMap.get(taskId);
            return CollectionUtils.isSubCollection(types,typeSet);
        }
        return false;
    }
}
