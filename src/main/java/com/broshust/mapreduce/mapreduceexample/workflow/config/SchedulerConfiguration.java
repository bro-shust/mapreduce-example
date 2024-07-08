package com.broshust.mapreduce.mapreduceexample.workflow.config;

import javax.sql.DataSource;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class SchedulerConfiguration {

    @Value("${mapreduce.scheduler.lock-table-name}")
    public String schedulerLockTableName;

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                                                      .withTableName(schedulerLockTableName)
                                                      .withJdbcTemplate(new JdbcTemplate(dataSource))
                                                      .usingDbTime()
                                                      .build()
        );
    }
}