

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class HouseholdHandler {
    
    private Household[] householdArr;
    private int nextId;
    
    public static final String DATA_SOURCE = "household.txt";
    
    
    
    public HouseholdHandler() { //Takes in nothing  reads from DATA_SOURCE and inserts data into the householdArr  Returns nothing
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            householdArr = new Household[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE));
            count = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            for (int i = 0; i < householdArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                int occupants = (Integer.parseInt(fields[1]));
                String streetAddress = fields[2];
                String zipCode = fields[3];
                String coordinates = fields[4] + "," + fields[5];
                householdArr[count] = new Household(id, occupants, streetAddress, zipCode, coordinates);
                count++;
            }

        } catch (Exception e) {
            householdArr = null;
        }
    }
    
    
    public Household[] getHouseholdArr() { //Takes in nothing  Does nothing  Returns householdArr 
        return householdArr;
    }

    public void setHouseholdArr(Household[] newHouseholdArr) { //Takes in Household array newHouseholdArr  Modifies id of instance to be newHouseholdArr supplied to method  Returns nothing
        householdArr = newHouseholdArr;
    }

    public int getNextId() { //Takes in nothing  Does nothing  Returns the risk level of the citizen “Phase”+ {“One” OR ”Two”OR “Three” OR “Four”}
        return nextId;
    }

    public void setNextId(int newNextId) { //Takes in integer newNextId  Modifies id of instance to be newNextId supplied to method  Returns nothing
        nextId = newNextId;
    }
    
    public Household findHousehold(int soughtId) { //Takes in integer soughtId  Finds the Household in the householdArr that has soughtId as id  Returns the household
        Household position = null;
        for (int i = 0; i < householdArr.length; i++) {
            if (householdArr[i].getId() == soughtId) {
                position = householdArr[i];
            }
        }
        return position;
    }
    public int findHouseholdPos(int soughtId) { //Takes in integer soughtId  Finds the Household in the householdArr that has soughtId as id  Returns the position of the household
        int position = -1;
        for (int i = 0; i < householdArr.length; i++) {
            if (householdArr[i].getId() == soughtId) {
                position = i;
            }
        }
        return position;
    }
    
    
}
