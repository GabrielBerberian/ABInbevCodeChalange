package org.abinbev.actuator;

import org.bson.Document;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.springframework.boot.actuate.health.Status.DOWN;
import static org.springframework.boot.actuate.health.Status.UP;

/**
 * Health indicator for MongoDB
 */
@Component
public class MongoHealthIndicator implements HealthIndicator {

    private final MongoTemplate mongoTemplate;

    public MongoHealthIndicator(MongoTemplate mongoTemplate) {
        Assert.notNull(mongoTemplate, "MongoTemplate must not be null");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Health health() {
        try {
            Document result = this.mongoTemplate.executeCommand("{ buildInfo: 1 }");
            if (result == null) {
                return Health.status(DOWN).build();
            }
            return Health.status(UP).withDetail("version", result.getString("version")).build();
        } catch (Exception e) {
            return Health.status(DOWN).build();
        }

    }
}