
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class VaccinationHandler {
    private Vaccination[] vaccinationArr;
    private int nextId;
    
    public static final String DATA_SOURCE = "vaccination.txt";
 
    public VaccinationHandler(Citizen[] citizenArr, Brand[] brandArr, Centre[] centreArr) { //takes in three arrays - reads from file - returns nothing
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            vaccinationArr = new Vaccination[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE));
            count = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            for (int i = 0; i < vaccinationArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                String date = fields[1];
                int centreId = (Integer.parseInt(fields[2]));
                int citizenId = (Integer.parseInt(fields[3]));
                int brandId = (Integer.parseInt(fields[4]));
                vaccinationArr[count] = new Vaccination(id, date, centreArr[CentreHandler.findCentrePos(centreId,centreArr)], citizenArr[CitizenHandler.findCitizenPos(citizenId,citizenArr)], brandArr[BrandHandler.findBrandPos(brandId,brandArr)]);
                
                count++;

            }

        } catch (Exception e) {

        }
    }

    public Vaccination[] getVaccinationArr() { //takes in nothing - returns vaccination array
        return vaccinationArr;
    }

    public void setVaccinationArr(Vaccination[] newVaccinationArr) {
        vaccinationArr = newVaccinationArr;
    }

    
    public int getNextId() { //takes in nothing - returns next id
        return nextId; 
    }

    public void setNextId(int newNextId) {
        nextId = newNextId;
    }
    
    public void sortByCentre(boolean up){
        if (up){
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getCentre().getName().compareTo(vaccinationArr[j + 1].getCentre().getName()) > 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        } else {
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getCentre().getName().compareTo(vaccinationArr[j + 1].getCentre().getName()) < 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        }
    }
    public void sortByBrand(boolean up){
        if (up){
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getBrand().getName().compareTo(vaccinationArr[j + 1].getBrand().getName()) > 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        } else {
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getBrand().getName().compareTo(vaccinationArr[j + 1].getBrand().getName()) < 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        }
    }
    
    public void sortByCitizen(boolean up){
        if (up){
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getCitizen().getFirstName().compareTo(vaccinationArr[j + 1].getCitizen().getFirstName()) > 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        } else {
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getCitizen().getFirstName().compareTo(vaccinationArr[j + 1].getCitizen().getFirstName()) < 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        }
    }
    
    public void sortByDate(boolean up){
        if (up){
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getDate().compareTo(vaccinationArr[j + 1].getDate()) > 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        } else {
            for (int i = 0; i < vaccinationArr.length; i++) {
                for (int j = 0; j < vaccinationArr.length - 1; j++) {
                    if (vaccinationArr[j].getDate().compareTo(vaccinationArr[j + 1].getDate()) < 0) {
                        Vaccination copy = vaccinationArr[j];
                        vaccinationArr[j] = vaccinationArr[j + 1];
                        vaccinationArr[j + 1] = copy;
                    }
                }
            }
        }
    }
    
    public static int findVaccinationPos(int soughtId, Vaccination[] vaccinationArr) { //find the position of a vaccination
        
        int position = -1;
        for (int i = 0; i < vaccinationArr.length; i++) {
            if (vaccinationArr[i].getId() == soughtId) {
                position = i;
            }
        }

        return position;
    }
    

    
    
}
