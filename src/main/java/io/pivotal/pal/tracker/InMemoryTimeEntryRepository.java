package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    long latestId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(latestId);
        timeEntries.put(latestId, timeEntry);
        latestId++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry oldTimeEntry = timeEntries.get(timeEntryId);
        if (oldTimeEntry == null ) {
            timeEntry = null;
        } else {
            timeEntry.setId(timeEntryId);
            timeEntries.put(timeEntryId, timeEntry);
        }
        return timeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntries.remove(timeEntryId);
    }
}
