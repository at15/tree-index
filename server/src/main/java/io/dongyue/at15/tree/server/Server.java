package io.dongyue.at15.tree.server;

import io.dongyue.at15.tree.server.health.DummyHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 16-1-1.
 */
public class Server extends Application<ServerConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    @Override
    public String getName() {
        return "tree-index-server";
    }


    @Override
    public void run(ServerConfig configuration,
                    Environment environment) throws Exception {
        // A dummy health check to avoid dropwizard warning
        final DummyHealthCheck healthCheck = new DummyHealthCheck();
        environment.healthChecks().register("dummy", healthCheck);
        // API

    }

    public static void main(String args[]) throws Exception {
        Server server = new Server();
        server.run(args);
    }
}
