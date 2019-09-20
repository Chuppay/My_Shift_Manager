package shiftman.server;

import java.util.ArrayList;
import java.util.List;

public class Shift extends TimeIntervalInDay {

    private final String _day;
    private final int _minimumWorkers;
    private List<Staff> _shiftWorkers;

    public Shift( String day ,String startTime, String endTime,String minimumWorkers) {
        super(startTime, endTime);
        _minimumWorkers = Integer.parseInt(minimumWorkers);
        _shiftWorkers = new ArrayList();
        _day=day;
    }

    public void assignStaff(Staff staff){
        _shiftWorkers.add(staff);
        staff.assignShift(this);
    }

    public boolean hasManager(){
        boolean manager = false;
        for (Staff staff : _shiftWorkers){
            if (staff.managerStatus() == true){
                manager = true;
            }
        }
        return manager;
    }

    public boolean isUnderstaffed(){
        if (_shiftWorkers.size() < _minimumWorkers){
            return true;
        } else {
            return false;
        }
    }

    public List<Staff> getStaff(){
        return _shiftWorkers;
    }

    @Override
    public String toString() {
        return _day + " " + _startTime + "-" + _endTime;
    }
}
