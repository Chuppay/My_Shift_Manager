package shiftman.server;

import java.time.LocalTime;

public abstract class TimeIntervalInDay {

    protected final LocalTime _startTime;
    protected final LocalTime _endTime;

    public TimeIntervalInDay(String startTime, String endTime) {
        _startTime = LocalTime.parse(startTime);
        _endTime = LocalTime.parse(endTime);
    }

    public LocalTime getStartTime(){
        return _startTime;
    }

    public LocalTime getEndTime(){
        return _endTime;
    }

}
