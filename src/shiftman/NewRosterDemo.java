package shiftman;


import shiftman.server.ShiftManager;
import shiftman.server.ShiftManagerServer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class NewRosterDemo {
	public static void main(String[] args) {
		System.out.println(">>Starting new roster demo");
		ShiftManager scheduler = new ShiftManagerServer();
		System.out.println(">>Setting working hours without having created new roster");
		// Put status in {} so can tell when get empty string.
		System.out.println(">>Create new roster 'eScooters R Us' (status of {} means no error)");
		scheduler.newRoster("eScooters R Us");
		scheduler.registerStaff("My", "Dude",Boolean.TRUE);
	
		System.out.println(">>Set working hours for Monday to 09:00-17:00");
		scheduler.setWorkingHours("MONDAY", "09:00", "17:00");
		try {
			scheduler.addShift("MONDAY","09:00","12:00","2");
			scheduler.assignStaff("MONDAY","09:00","12:00","My","Dude");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.bin"));
			os.writeObject(scheduler);
			os.close();
			System.out.println(scheduler.displayRoster());
			System.out.println("Done");
		} catch (Exception e){
			e.printStackTrace();
		}


	}
}
