/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author thandeka.matrose
 */
public class Centre {
    private int id = -1;
    private String name = "Unknown";
    private String streetAddress = "Unknown";
    private String zipCode = "0000";
    private String coordinates = "0,0";
    private int vaccinesReceived = 0;
    private int vaccinesAdministered = 0;
    
    public static final Centre CAPETOWN_HEALTH_CENTRE = new Centre(-1, "Undelivered", "4 Dorp St", "8000", "-33.9242677,18.4180307" , 0, 0);

    public Centre() {
        
    }
    
    public Centre(int newId, String newName , String newAddress, String newZipCode, String newCoordinates, int newVaccinesReceived, int newVaccinesAdministered) {
        setId(newId);
        setName(newName);
        setVaccinesReceived(newVaccinesReceived);
        setVaccinesAdministered(newVaccinesAdministered);
        setCoordinates(newCoordinates);
        setStreetAddress(newAddress);
        setZipCode(newZipCode);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int newId) {
        if (newId>=-1){
            id = newId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        if (newName.length()>0){
            name = newName;
        }
    }

    public int getVaccinesReceived() {
        return vaccinesReceived;
    }

    public void setVaccinesReceived(int newVaccinesReceived) {
        if(newVaccinesReceived>=0){
            vaccinesReceived = newVaccinesReceived;
        }
    }
    
    public int getAvailableVaccines() {
        return vaccinesReceived-vaccinesAdministered;
    }
    
    public String getCoordinates() {
        return coordinates;
    }
    public String getCoordinateString(){
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

    public void setCoordinates(String newCoordinates) {
        coordinates = newCoordinates;
    }
    
    public double getLatitude() {
        return Double.parseDouble(coordinates.split(",")[0]);
    }
    
    public double getLongitude() {
        return Double.parseDouble(coordinates.split(",")[1]);
    }
    
    public void setLatitude(double latitude){
        String[] coordinatesArr = coordinates.split(",");
        coordinates = (latitude) +","+ coordinatesArr[1];
    }
    
    public void setLongitude(double longitude){
        String[] coordinatesArr = coordinates.split(",");
        coordinates = coordinatesArr[0] +","+ (longitude);
    }
    
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String address) {
        if (address.length()>0){
            streetAddress = address;
        }
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String newZipCode) {
        if (newZipCode.length() == 4){
            zipCode = newZipCode;
        }
    }
    
    public int getVaccinesAdministered() {
        return vaccinesAdministered;
    }

    public void setVaccinesAdministered(int newVaccinesAdministered) {
        if (newVaccinesAdministered>=0){
            this.vaccinesAdministered = newVaccinesAdministered;
        }
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
