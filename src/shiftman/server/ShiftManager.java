package shiftman.server;

import java.util.List;

/**
 * Manages the roster for a shop, that is, the assignment of staff for a shop
 * to shifts for a working week.
 *
 * A shift is a continuous period of time that has staff assigned to it for the
 * entire period. The time will be given in a string in ISO-8601 format without
 * without a time zone or seconds (e.g. 10:30).
 *
 * Each shift should have a certain number of workers and at least
 * one manager (managers count as a worker), shifts which have less than that
 * number (specified when creating the shift), are considered understaffed. Only
 * registered staff can be assigned to a shift.
 *
 * Some of the methods return a list of staff or shifts. The list of staff will
 * be sorted in order by their given name. The list of shifts will be sorted by
 * the day of the week, then by their start time.
 */

public interface ShiftManager {

    /**
     * @param shopName The name of the shop the roster is for.
     * @return The status of the request.
     */
    public void newRoster(String shopName);

    /**
     * @param dayOfWeek The day the request applies to.
     * @param startTime The start of the working day.
     * @param endTime The end of the working day.
     * @return The status of the request.
     */
    public void setWorkingHours(String dayOfWeek, String startTime, String endTime);

    /**
     * @param dayOfWeek The day the request applies to.
     * @param startTime The start of the shift.
     * @param endTime The end of the shift.
     * @param minimumWorkers The number of workers needed for the shift.
     * @return The status of the request
     */
    public void addShift(String dayOfWeek, String startTime, String endTime, String minimumWorkers) throws Exception;

    /**
     * @param givenName The given name of the staff member
     * @param familyName The family name of the staff member
     * @param isManager True if the staff member is a manager
     * @return The status of the request as described in the notes.
     */
    public void registerStaff(String givenName, String familyName, Boolean isManager);

    /**
     * @param dayOfWeek The day the request applies to.
     * @param startTime The start of the shift.
     * @param endTime The end of the shift.
     * @param givenName The given name of the staff member
     * @param familyName The family name of the staff member
     * @return The status of the request as described in the notes.
     */
    public void assignStaff(String dayOfWeek, String startTime, String endTime, String givenName, String familyName);

    /**
     * @return A list of all registered staff.
     */
    public List<Staff> getRegisteredStaff();

    /**
     * @return A list of all unassigned staff.
     */
    public List<Staff> getUnassignedStaff();

    /**
     * @return A list shifts that do not have a manager assigned.
     */
    public List<Shift> getShiftsWithoutManagers();

    /**
     * @return A list shifts that have fewer workers than the minimum required.
     */
    public List<Shift> getUnderstaffedShifts();

    /**
     * @param dayOfWeek The day of the week to provide the roster for.
     * @return A list of shifts for the specified day
     */
    public List<Shift> getShiftsForDay(String dayOfWeek);

    /**
     * @param workerName The name of the staff member
     * @return a list of shifts the staff member is a worker for.
     */
    public List<Shift> getRosterForWorker(String workerName);


    /**
     * @return a list of all the shifts registered.
     */
    public List<Shift> displayRoster();

    /**
     * @return true if the there are no issues with the roster.
     */
    public Boolean compatible();
}
