package shiftman.server;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkDay extends TimeIntervalInDay{

    private List<Shift> _dailyShifts;
    private final String _dayOfWeek;

    public WorkDay(String dayOfWeek, String startTime, String endTime) {
        super(startTime, endTime);
        _dailyShifts = new ArrayList<Shift>();
        _dayOfWeek = dayOfWeek;
    }

    public Boolean isInvalidShiftTime(String startTime, String endTime){

        Boolean isInvalid = false;
        LocalTime tempStartTime = LocalTime.parse(startTime);
        LocalTime tempEndTime = LocalTime.parse(endTime);

        if ((tempEndTime.isAfter(_endTime)) || (tempStartTime.isBefore(_startTime))){
            isInvalid = true;
        }

        // check for overlaps
        for (Shift shift : _dailyShifts){
                if (shift.getStartTime().isAfter(tempStartTime) && shift.getStartTime().isBefore(tempEndTime)){
                    isInvalid = true;
                } else if (shift.getStartTime().isBefore(tempStartTime) && shift.getEndTime().isAfter(tempStartTime)){
                    isInvalid = true;
                }
            }
        return isInvalid;
    }

    public void addShift(Shift shift) {
        _dailyShifts.add(shift);
    }

    public String getDay() {
        return _dayOfWeek;
    }

    public List<Shift> getShifts(){
        return _dailyShifts;
    }

    @Override
    public String toString() {
        return _dayOfWeek + " " + _startTime + " " + _endTime;
    }
}
