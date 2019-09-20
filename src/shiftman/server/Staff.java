package shiftman.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Serializable {

    private final String _givenName;
    private final String _familyName;
    private final Boolean _isManager;
    private List<Shift> _assignedShifts;

    public Staff(String givenName, String familyName, Boolean isManager) {
        _givenName = givenName;
        _familyName = familyName;
        _isManager = isManager;
        _assignedShifts = new ArrayList();
    }

    public boolean managerStatus(){
        return _isManager;
    }

    public void assignShift(Shift shift) {
        _assignedShifts.add(shift);
    }

    public List<Shift> getShifts(){
        return _assignedShifts;
    }

    @Override
    public String toString() {
        return _givenName + " " + _familyName;
    }

}
