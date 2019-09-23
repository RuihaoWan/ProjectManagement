package com.rio.proj.kafka;


import com.alibaba.fastjson.JSON;
import com.rio.proj.entity.MysqlRowData;
import com.rio.proj.websocket.WebSocketSender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component("kafkaListener")
public class Listener {

    @KafkaListener(topics = {"proj-search-mysql-data"},groupId = "proj-broadcast")
    public void processMysqlRowData(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            MysqlRowData rowData = JSON.parseObject(
                    message.toString(),
                    MysqlRowData.class
            );
            try {
                WebSocketSender.sendInfo(JSON.toJSONString(rowData));
            } catch (IOException ex) {
                System.out.println("Client connect error");
            }
            System.out.println("kafka processMysqlRowData: " +
                    JSON.toJSONString(rowData));
        }
    }
}
