package io.dongyue.at15.tree.server.resources;

import io.dongyue.at15.tree.server.client.HDFSClient;
import io.dongyue.at15.tree.server.managed.HDFSClientManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by at15 on 16-1-1.
 */
@Path("/")
public class IndexResource {
    private HDFSClient client;

    public IndexResource(HDFSClientManager hdfsClientManager) {
        client = hdfsClientManager.getClient();
    }

    @GET
    public String index() {
        return client.getHDFS().getCanonicalServiceName();
    }
}
