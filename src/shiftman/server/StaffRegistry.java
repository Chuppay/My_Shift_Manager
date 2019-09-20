package shiftman.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StaffRegistry implements Serializable {

    private List<Staff> _staffList;

    public StaffRegistry(){
        _staffList = new ArrayList();
    }

    public void addStaff(Staff staff) {
        _staffList.add(staff);
    }

    public boolean isRegistered(String givenName, String familyName){
        for (Staff staff : _staffList){
            if (staff.toString().equals(givenName+" "+familyName));
            return true;
        }
        return false;
    }

    public Staff fetchStaff(String givenName, String familyName){
        for (Staff staff : _staffList){
            if (staff.toString().equals(givenName + " " + familyName)){
                return staff;
            }
        }
        return null;
    }

    public List<Staff> getUnassignedStaff(){
        List<Staff> temp = new ArrayList();
        for (Staff staff : _staffList){
            if (staff.getShifts() == null){
                temp.add(staff);
            }
        }
        return temp;
    }

    public List<Staff> getStaffList(){
        return _staffList;
    }

    public List<Shift> getShiftsFor(String workerName) {
        for (Staff staff :_staffList){
            if (workerName.equals(staff.toString())){
                return staff.getShifts();
            }
        }
        return null;
    }
}
