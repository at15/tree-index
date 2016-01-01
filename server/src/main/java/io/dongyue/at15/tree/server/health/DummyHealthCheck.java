package io.dongyue.at15.tree.server.health;

import com.codahale.metrics.health.HealthCheck;
/**
 * Created by at15 on 16-1-1.
 */
public class DummyHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}