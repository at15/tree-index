package io.dongyue.at15.tree.server;

import io.dongyue.at15.tree.server.manager.IndexManager;
import io.dongyue.at15.tree.server.manager.MetaManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        MetaManager.init();
        IndexManager.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
