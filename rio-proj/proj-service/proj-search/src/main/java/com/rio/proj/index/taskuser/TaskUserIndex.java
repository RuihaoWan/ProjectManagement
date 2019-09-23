package com.rio.proj.index.taskuser;

import com.rio.proj.index.IndexAware;
import com.rio.proj.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class TaskUserIndex implements IndexAware<Long, Set<Long>> {

    private static Map<Long,Set<Long>> taskUserMap;
    private static Map<Long,Set<Long>> userTaskMap;

    static {
        taskUserMap = new ConcurrentHashMap<>();
        userTaskMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(Long key) {
        //most case get a task list of a user
        return userTaskMap.get(key);
    }

    @Override
    public void add(Long key, Set<Long> value) {
        log.info("Add users to a task, before add: {}",taskUserMap);
        Set<Long> userIds = CommonUtils.getOrCreate(
                key,taskUserMap, ConcurrentSkipListSet::new
        );
        //return new ConcurrentSkipListSet or userList
        userIds.addAll(value); //value->userIds
        for(Long userId:value){
            Set<Long> its = CommonUtils.getOrCreate(
                    userId,userTaskMap,
                    ConcurrentSkipListSet::new
            );
            its.add(key);
        }
        log.info("Add users to a task, after add: {}",taskUserMap);
    }

    @Override
    public void update(Long key, Set<Long> value) {
        log.error("Not support");
    }

    @Override
    public void delete(Long key, Set<Long> value) {
        //most case delete a task from a user list
        log.info("Delete users from a task, before delete: {}",userTaskMap);
        Set<Long> userIds = CommonUtils.getOrCreate(
                key,taskUserMap,ConcurrentSkipListSet::new
        );
        userIds.removeAll(value);
        for(Long userId : value) {
            Set<Long> taskSet = CommonUtils.getOrCreate(
                    userId,userTaskMap,ConcurrentSkipListSet::new
            );
            taskSet.remove(key);
        }
        log.info("Delete users from a task, after delete: {}",userTaskMap);
    }
}
