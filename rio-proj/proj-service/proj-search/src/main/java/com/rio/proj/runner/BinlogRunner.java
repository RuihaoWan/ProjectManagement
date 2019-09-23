package com.rio.proj.runner;

import com.rio.proj.mysql.BinlogClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BinlogRunner implements CommandLineRunner {
    private final BinlogClient client;

    @Autowired
    public BinlogRunner(BinlogClient client){this.client = client;}

    @Override
    public void run(String... args) throws Exception {
        log.info("Coming in BinlogRunner...");
        client.connect();
    }
}