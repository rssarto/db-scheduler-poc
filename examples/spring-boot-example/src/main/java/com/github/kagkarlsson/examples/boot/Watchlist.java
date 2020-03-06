package com.github.kagkarlsson.examples.boot;

import com.github.kagkarlsson.scheduler.task.ExecutionComplete;
import com.github.kagkarlsson.scheduler.task.schedule.Schedule;

import java.time.Instant;
import java.time.LocalDateTime;

public class Watchlist {

    private WatchlistSchedule watchlistSchedule;
    private LocalDateTime nextExecutionDate;

    public Watchlist(){
        this.watchlistSchedule = new WatchlistSchedule();
        nextExecutionDate = LocalDateTime.now().plusSeconds(5);
    }

    public LocalDateTime getInitialExecutionDate(){
        return nextExecutionDate;
    }

    public LocalDateTime getNextExecutionDate() {
        nextExecutionDate = nextExecutionDate.plusSeconds(10);
        return nextExecutionDate;
    }

    public WatchlistSchedule getWatchlistSchedule() {
        return watchlistSchedule;
    }

    public class WatchlistSchedule implements Schedule {
        @Override
        public Instant getNextExecutionTime(ExecutionComplete executionComplete) {
            return Instant.from(Watchlist.this.getNextExecutionDate());
        }

        @Override
        public Instant getInitialExecutionTime(Instant now) {
            return Instant.from(Watchlist.this.getInitialExecutionDate());
        }
    }
}
