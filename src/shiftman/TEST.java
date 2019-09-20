package shiftman;


import shiftman.server.ShiftManagerServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TEST {

    public static void main(String[] args) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("data.bin"));
            ShiftManagerServer SM = (ShiftManagerServer) is.readObject();
            is.close();
            System.out.println(SM.displayRoster());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
