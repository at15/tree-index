package io.dongyue.at15.tree.server;

import io.dongyue.at15.tree.server.manager.IndexManager;
import io.dongyue.at15.tree.server.manager.MetaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.hadoop.fs.FsShell;

@SpringBootApplication
@ImportResource("beans.xml")
public class ServerApplication implements CommandLineRunner {
    @Autowired
    private FsShell shell;

    @Override
    public void run(String... strings) throws Exception {
        MetaManager.init(shell);
        IndexManager.init(shell);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
