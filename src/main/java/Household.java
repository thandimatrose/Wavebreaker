


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author thandeka.matrose
 */
public class Household {
    private int id = -1;
    int occupants = 0;
    String streetAddress = "NONE_PROVIDED";
    String zipCode = "NONE_PROVIDED";
    String coordinates = "NONE PROVIDED";
    
    
    public Household(int newId, int newOccupants, String newStreetAddress, String newZipCode, String newCoordinates) {
        setId(newId);
        setOccupants(newOccupants);
        setStreetAddress(newStreetAddress);
        setZipCode(newZipCode);
        setCoordinates(newCoordinates);
    }

    public Household() {
        int id = -1;
        occupants = 0;
        streetAddress = "NONE_PROVIDED";
        zipCode = "NONE_PROVIDED";
        coordinates = "NONE PROVIDED";
    
    }

    public int getId() { //Takes in nothing  Does nothing  Returns the id attribute of Household object
        return id;
    }

    public void setId(int newId) { //Takes in an integer newId  Modifies id attribute of object to be newId  Returns nothing
        this.id = newId;
    }

    public int getOccupants() { //Takes in nothing  Does nothing  Returns the occupants attribute of Household object
        return occupants;
    }

    public void setOccupants(int newOccupants) {  //Takes in an integer newOccupants  Modifies occupants of object to be newOccupants supplied to method  Returns nothing
        if (newOccupants >= 0 && newOccupants < 50){
            occupants = newOccupants;
        }
    }
    
    public String getStreetAddress() { //Takes in nothing  Does nothing  Returns the streetAddress attribute of Household object
        return streetAddress;
    }

    public void setStreetAddress(String newStreetAddress) { //Takes in a string newStreetAddress  Modifies streetAddress of instance to be newStreetAddress supplied to method  Returns nothing
        streetAddress = newStreetAddress;
    }

    public String getZipCode() { //Takes in nothing  Does nothing  Returns the Cape Town zipCode attribute of Household object
        return zipCode;
    }

    public void setZipCode(String newZipCode) { //Takes in a string newZipCode  Modifies zipCode of instance to be newZipCode supplied to method  Returns nothing
        zipCode = newZipCode;
    }
    
    public String getCoordinates() { //Takes in nothing  Does nothing  Returns the coordinates of the Household object formatted as such <latitude>,<longitude>
        return coordinates;
    }
    public String getCoordinateString(){  //Takes in nothing  Calculates degrees, minutes and seconds  Returns the coordinates of the Household object formatted as <degrees> <minutes>’ <seconds>’
        String toReturn = "";
        String[] details = coordinates.split(",");
        double latitude = Double.parseDouble(details[0])*-1.0;
        int degrees = (int)(latitude);
        int minutes = (int)((latitude-degrees)*60);
        double num = ((((latitude-degrees)*60)-minutes)*60.0);
        int seconds = (int)((((latitude-degrees)*60)-minutes)*60);
        if (num-seconds>0.5){
            seconds++;
        }
        toReturn = toReturn + (degrees+"° "+minutes+"' "+ seconds+ "''S ");
        double longitude = Double.parseDouble(details[1]);
        degrees = (int)(longitude);
        minutes = (int)((longitude-degrees)*60);
        num = ((((longitude-degrees)*60)-minutes)*60);
        seconds = (int)((((longitude-degrees)*60)-minutes)*60);
        if (num-seconds>0.5){
            seconds++;
        }
        toReturn = toReturn + (degrees+"° "+minutes+"' "+ seconds+ "''E ");
        return toReturn;
    }
    
    public double getLatitude() { //Takes in nothing  Does nothing  Returns the decimal value of the Latitude portion of the coordinates
        return Double.parseDouble(coordinates.split(",")[0]);
    }
    
    public double getLongitude() { //Takes in nothing  Does nothing  Returns the decimal value of the Longitude portion of the coordinates
        return Double.parseDouble(coordinates.split(",")[1]);
    }
    
    public void setLatitude(double latitude){ //Takes in a real newLatitude  Modifies <latitude> part of coordinates of object to be newCoordinates supplied to method  Returns nothing
        String[] coordinatesArr = coordinates.split(",");
        coordinates = (latitude) +","+ coordinatesArr[1];
    }
    
    public void setLongitude(double longitude){ //Takes in an integer newLatitude  Modifies <longitude> part of coordinates of object to be newOccupants supplied to method  Returns nothing
        String[] coordinatesArr = coordinates.split(",");
        coordinates = coordinatesArr[0] +","+ (longitude);
    }

    public void setCoordinates(String coordinates) { //Takes in a string newCoordinates  Modifies coordinates of object to be newCoordinates supplied to method  Return nothing
        this.coordinates = coordinates;
    }

    @Override
    public String toString() { //Takes in nothing  Does nothing  Returns streetAdress
        return streetAddress;
    }
    
    
    
}
