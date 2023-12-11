 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author thandeka.matrose
 */
public class Vaccination {
    private int id = 0;
    String date = "";
    Centre centre = new Centre();
    Citizen citizen = new Citizen();
    Brand brand = new Brand();

    public Vaccination() {
        
    }
    
    public Vaccination(int newId, String newDate, Centre centreId, Citizen citizenId, Brand brandId) {
        setVaccinationId(newId);
        setDate(newDate);
        setCentre(centreId);
        setCitizen(citizenId);
        setBrand(brandId);
    }

    public int getId() {
        return this.id;
    }

    public void setVaccinationId(int vaccinationId) {
        id = vaccinationId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String newDate) {
        date = newDate;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre newCentre) {
        centre = newCentre;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen newCitizen) {
        citizen = newCitizen;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand newBrand) {
        brand = newBrand;
    }

    @Override
    public String toString() {
        String fullInfo = this.getCitizen().getFullName()+ " (aged "+ this.getCitizen().getAge()+" yrs)\n";
        fullInfo = fullInfo + this.getCitizen().getRiskLevelString()+"\n";
        fullInfo = fullInfo + "Contact Number: " + this.getCitizen().getContactNumber()+"\n";
        fullInfo = fullInfo + "Vaccination Administered at " + this.getCentre().getName() +"\n";
        fullInfo = fullInfo + "Date: " + this.getDate()+"\n";
        fullInfo = fullInfo + "Brand: " + this.getBrand().getCommonName();
        return fullInfo;
    }
    
}
