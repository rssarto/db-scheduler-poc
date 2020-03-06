package com.github.kagkarlsson.examples.boot;

import com.github.kagkarlsson.scheduler.task.Task;
import com.github.kagkarlsson.scheduler.task.helper.RecurringTask;
import com.github.kagkarlsson.scheduler.task.helper.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private final SchedulerService schedulerService;

    @Autowired
    public TaskService(final SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @PostConstruct
    public void schedule(){
        for(int i = 0; i < 10; i++){
            final Watchlist watchlist = new Watchlist();
            Task<Void> rtWatchlist = Tasks
                .recurring("watch-list_" + i, watchlist.getWatchlistSchedule())
                .execute((instance, ctx) -> {
                    log.info("Running recurring watchlist-task. Instance: {}, ctx: {}", instance, ctx);
                });
            this.schedulerService.schedule(rtWatchlist, "recurring", watchlist.getInitialExecutionDate().atZone(ZoneId.systemDefault()).toInstant());
        }
    }

}
