package shiftman.server;

import java.util.List;
import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class ShiftManagerServer implements ShiftManager {

    private String _shopName;
    private StaffRegistry _registeredStaff;
    private Roster _roster;


    @Override
    public void newRoster(String shopName) {
        if ((shopName == null) || (shopName.equals(""))){
            throw new IllegalArgumentException("Supplied name for shop is invalid.");
        } else {
            _shopName = shopName;
            _registeredStaff = new StaffRegistry();
            _roster = new Roster();
        }
        return;
    }

    @Override
    public void setWorkingHours(String dayOfWeek, String startTime, String endTime) {
        Boolean isValidDay = false;
        for (DayOfWeek days : DayOfWeek.values()) {
            if ((days.toString()).equals(dayOfWeek)) {
                isValidDay = true;
            }
        }

        if (isValidDay == false){
            throw new IllegalArgumentException("The supplied day is not one of the 7 days of the week.");
        } else {
            LocalTime tempEndTime =  LocalTime.parse(endTime);
            LocalTime tempStartTime = LocalTime.parse(startTime);
            if (tempEndTime.isAfter(tempStartTime) && (tempEndTime.isBefore(LocalTime.MAX))) {
                _roster.addWorkDay(new WorkDay(dayOfWeek,startTime,endTime));
            } else {
                throw new IllegalArgumentException("Time(s) supplied is not valid.");
            }
        }
        return;
    }

    @Override
    public void addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) throws Exception {
        _roster.addShift(dayOfWeek,startTime,endTime,minimumWorkers);
    }

    @Override
    public void registerStaff(String givenName, String familyName, Boolean isManager) {
        if ((givenName == null) || (familyName == null) || (isManager == null)){
            throw new IllegalArgumentException("The supplied name(s) are invalid.");
        }
        Staff staff = new Staff(givenName, familyName, isManager);
        _registeredStaff.addStaff(staff);
        return;
    }

    @Override
    public void assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName) {
        if (!_registeredStaff.isRegistered(givenName,familyName)){
            throw new IllegalArgumentException("The name of staff is not registered");
        }
        Shift candidateShift = _roster.registeredShift(dayOfWeek,startTime,endTime);
        if (candidateShift == null){
            throw new IllegalArgumentException("The shift is not registered");
        }
        candidateShift.assignStaff(_registeredStaff.fetchStaff(givenName,familyName));
    }

    @Override
    public List<Staff> getRegisteredStaff() {
        return _registeredStaff.getStaffList();
    }

    @Override
    public List<Staff> getUnassignedStaff() {
        return _registeredStaff.getUnassignedStaff();
    }

    @Override
    public List<Shift> getShiftsWithoutManagers() {
        return _roster.getShiftsWithCondition("Unmanaged");
    }

    @Override
    public List<Shift> getUnderstaffedShifts() {
        return _roster.getShiftsWithCondition("Understaffed");
    }

    @Override
    public List<Shift> getShiftsForDay(String dayOfWeek) {
        return _roster.getShiftsForDay(dayOfWeek);
    }

    @Override
    public List<Shift> getRosterForWorker(String workerName) {
        return _registeredStaff.getShiftsFor(workerName);
    }

    @Override
    public List<Shift> displayRoster() {
        return _roster.getShiftsWithCondition("All");
    }

    @Override
    public Boolean compatible() {
        if ((getUnderstaffedShifts() == null) && (getShiftsWithoutManagers() == null)){
            return true;
        } else {
            return false;
        }
    }

}
