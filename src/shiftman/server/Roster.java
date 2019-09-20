package shiftman.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Roster implements Serializable {

    private List<WorkDay> _workDays;

    public Roster() {
        _workDays = new ArrayList();
    }

    public void addWorkDay(WorkDay workDay) {
        _workDays.add(workDay);
    }

    public void addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) {
        for (WorkDay workDay : _workDays) {
            if (workDay.getDay().equals(dayOfWeek)) {
                if (workDay.isInvalidShiftTime(startTime, endTime)) {
                    throw new IllegalArgumentException("Shift time supplied is not valid");
                } else {
                    workDay.addShift(new Shift(dayOfWeek,startTime, endTime, minimumWorkers));
                    return;
                }
            }
        }
        throw new IllegalArgumentException(dayOfWeek + " is not registered as a work day");
    }

    public Shift registeredShift(String dayOfWeek, String startTime, String endTime) {
        for (WorkDay workday : _workDays) {
            List<Shift> shifts = workday.getShifts();
            for (Shift shift : shifts) {
                if (shift.toString().equals(dayOfWeek + " " + startTime + "-" + endTime)) {
                    return shift;
                }
            }
        }
        return null;
    }

    public List<Shift> getShiftsWithCondition(String condition) {
        List<Shift> temp = new ArrayList();
        for (WorkDay workDay : _workDays) {
            for (Shift shift : workDay.getShifts()) {
                if (condition.equals("Unmanaged")) {
                    if (!shift.hasManager()) {
                        temp.add(shift);
                    }
                } else if (condition.equals("Understaffed")) {
                    if (shift.isUnderstaffed()) {
                        temp.add(shift);
                    }
                } else if (condition.equals("All")){
                    temp.add(shift);
                }
            }
        }
        return temp;
    }

    public List<Shift> getShiftsForDay(String dayOfWeek) {
        for (WorkDay workDay : _workDays) {
            if (workDay.getDay().equals(dayOfWeek)) {
                return workDay.getShifts();
            }
        }
        return null;
    }

}
