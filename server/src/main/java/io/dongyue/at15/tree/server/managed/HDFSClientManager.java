package io.dongyue.at15.tree.server.managed;

import io.dongyue.at15.tree.server.client.HDFSClient;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by at15 on 16-1-1.
 */
public class HDFSClientManager implements Managed {
    private HDFSClient client;

    public HDFSClientManager(HDFSClient client) {
        this.client = client;
    }

    public HDFSClient getClient() {
        return client;
    }

    public void start() throws Exception {
        client.start();
    }

    public void stop() throws Exception {
        client.stop();
    }
}
