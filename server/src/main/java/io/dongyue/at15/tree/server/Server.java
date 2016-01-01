package io.dongyue.at15.tree.server;

import io.dongyue.at15.tree.server.client.HDFSClient;
import io.dongyue.at15.tree.server.health.DummyHealthCheck;
import io.dongyue.at15.tree.server.managed.HDFSClientManager;
import io.dongyue.at15.tree.server.resources.IndexResource;
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

        // Managed object
        HDFSClient client = new HDFSClient();
        HDFSClientManager hdfsClientManager = new HDFSClientManager(client);
        environment.lifecycle().manage(hdfsClientManager);
        // TODO: how to get it?, pass it in constructor...

        environment.jersey().register(new IndexResource(hdfsClientManager));

    }

    public static void main(String args[]) throws Exception {
        Server server = new Server();
        server.run(args);
    }
}
