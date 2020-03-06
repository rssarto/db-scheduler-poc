package com.github.kagkarlsson.examples.boot;

import com.github.kagkarlsson.scheduler.Scheduler;
import com.github.kagkarlsson.scheduler.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(final Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public void schedule(Task<Void> task, String instance, Instant instant){
        this.scheduler
            .schedule(
                task.instance(instance),
                instant
            );
    }

}
