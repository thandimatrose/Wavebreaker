


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class Brand {
    private int id = -1;
    private String name = "Unknown";
    private String commonName = "Unknown";
    private double efficacyRate = 00.0;
    private String countryOfOrigin = "Unknown";
    private int supplyReceived = 0;
    private int supplyAdministered = 0;
    
    public Brand(){
        
    }

    public Brand(int newId, String newName, String newCommonName, double newEfficacyRate, String newCountryOfOrigin, int newSupplyReceived, int newSupplyAdministered) {
        setId(newId);
        setName(newName);
        setCommonName(newCommonName);
        setEfficacyRate(newEfficacyRate);
        setCountryOfOrigin(newCountryOfOrigin);
        setSupplyAdministered(newSupplyAdministered);
        setSupplyReceived(newSupplyReceived);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int newId) {
        if (newId>-1) {
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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String newCommonName) {
        if (newCommonName.length()>0){
            commonName = newCommonName;
        }
    }

    public double getEfficacyRate() {
        return efficacyRate;
    }

    public void setEfficacyRate(double newEfficacyRate) {
        if (newEfficacyRate>0.0){
            efficacyRate = newEfficacyRate;
        }
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String newCountryOfOrigin) {
        if (newCountryOfOrigin.length()>0){
            countryOfOrigin = newCountryOfOrigin;
        }
    }

    public int getSupplyReceived() {
        return supplyReceived;
    }

    public void setSupplyReceived(int newSupplyReceived) {
        if (newSupplyReceived>=0){
            supplyReceived = newSupplyReceived;
        }
    }

    public int getSupplyAdministered() {
        return supplyAdministered;
    }

    public void setSupplyAdministered(int newSupplyAdministered) {
        if (newSupplyAdministered>=0){
            supplyAdministered = newSupplyAdministered;
        }
    }

    
    
    @Override
    public String toString() {
        return commonName;
    }
    
    
}
