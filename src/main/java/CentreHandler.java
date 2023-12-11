


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class CentreHandler {

    private Centre[] centreArr;
    private int nextId;
    
    public static final String DATA_SOURCE = "centre.txt";

    public CentreHandler() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            centreArr = new Centre[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE));
            count = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            for (int i = 0; i < centreArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String address = fields[2];
                String zipCode = fields[3];
                String coordinates = fields[4] + "," + fields[5];
                int vaccinesReceived = (Integer.parseInt(fields[6]));
                int vaccinesAdministered = (Integer.parseInt(fields[7]));

                centreArr[count] = new Centre(id, name, address, zipCode, coordinates, vaccinesReceived, vaccinesAdministered);
                count++;
            }

        } catch (Exception e) {
            centreArr = null;
        }
    }
    
    

    public Centre[] getCentreArr() {
        return centreArr;
    }

    public void setCentreArr(Centre[] newCentreArr) {
        centreArr = newCentreArr;
    }
    
    

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int newNextId) {
        nextId = newNextId;
    }
    
    
    
    public static int findCentrePos(int soughtId, Centre[] centreArr) {
        
        int position = -1;
        for (int i = 0; i < centreArr.length; i++) {
            if (centreArr[i].getId() == soughtId) {
                position = i;
            }
        }
        return position;
    }
    
    //Household Helper Methods
    //Method to find id of closest centre where certain household can get vaccine
    public Centre getClosestCentre(Household currentHousehold) {
        
        double lowestDistance = getDistanceKilometres(currentHousehold.getCoordinates(), centreArr[0].getCoordinates()); //default lowest distance is the distance from first centre in array because the rest will be analysed as well
        Centre lowestId = centreArr[0];//variable tracking the id to return
        for (int i = 0; i < centreArr.length; i++) { //compares supplied household to every centre
            double distance = getDistanceKilometres(currentHousehold.getCoordinates(), centreArr[i].getCoordinates()); //find distance
            if (distance < lowestDistance) { //if lowest centre that has vaccines left so far
                lowestDistance = distance; //lowest becomes this if lower
                lowestId = centreArr[i]; //required id
            }
        }
        return lowestId;
    }
    
    public static double getDistanceKilometres(String coordinates1, String coordinates2) {
        final int R = 6371; // Radius of the earth in km
        String[] coordinatesArr1 = coordinates1.split(",");
        String[] coordinatesArr2 = coordinates2.split(",");
        double lat1 = Double.parseDouble(coordinatesArr1[0]);
        double lon1 = Double.parseDouble(coordinatesArr1[1]);
        double lat2 = Double.parseDouble(coordinatesArr2[0]);
        double lon2 = Double.parseDouble(coordinatesArr2[1]);

        var dLat = (lat2 - lat1) * (Math.PI / 180);  // deg2rad below
        var dLon = (lon2 - lon1) * (Math.PI / 180);
        var a
                = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos((lat1) * (Math.PI / 180)) * Math.cos((lat2) * (Math.PI / 180))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;

    }
    
    
    
}
