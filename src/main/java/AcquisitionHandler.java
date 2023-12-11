
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class AcquisitionHandler {
    private Acquisition[] acquisitionArr;
    private int nextId;
    
    private Centre reserveCentre = Centre.CAPETOWN_HEALTH_CENTRE;
    
    public static final String DATA_SOURCE = "acquisition.txt";

    public AcquisitionHandler(Centre[] centreArr, Brand[] brandArr) {
        try {
            
            reserveCentre.setVaccinesAdministered(0);
            reserveCentre.setVaccinesReceived(0);
            
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            acquisitionArr = new Acquisition[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE));
            count = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            for (int i = 0; i < acquisitionArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                int doses = (Integer.parseInt(fields[1]));
                int brandId = (Integer.parseInt(fields[2]));
                int centreId = (Integer.parseInt(fields[3]));
                
                if (centreId != -1) {
                    acquisitionArr[count] = new Acquisition(id, doses, brandArr[BrandHandler.findBrandPos(brandId,brandArr)], centreArr[CentreHandler.findCentrePos(centreId,centreArr)]);
                    reserveCentre.setVaccinesAdministered(reserveCentre.getVaccinesAdministered() + doses);
                    
                } else {
                    
                    acquisitionArr[count] = new Acquisition(id, doses, brandArr[BrandHandler.findBrandPos(brandId,brandArr)], reserveCentre);
                    reserveCentre.setVaccinesReceived(reserveCentre.getVaccinesReceived() + doses);
                    
                }
                count++;
            }
        } catch (Exception e) {
            
        }
    }

    public Acquisition[] getAcquisitionArr() {
        return acquisitionArr;
    }

    public void setAcquisitionArr(Acquisition[] newAcquisitionArr) {
        acquisitionArr = newAcquisitionArr;
    }
    
    public int getNextId() {
        return nextId;
    }

    public void setNextId(int newNextId) {
        nextId = newNextId;
    }

    public Centre getReserveCentre() {
        return reserveCentre;
    }
    
    
    
    public int calcVaccinesReceived(int centreId) {
        
        int numVaccines = 0;
        for (int i = 0; i < acquisitionArr.length; i++) {
            if (acquisitionArr[i].getCentre().getId() == centreId) {
                numVaccines = numVaccines + acquisitionArr[i].getDoses();
            }
        }
        return numVaccines;
    }
    
    
    public int calcSupplyReceived(int brandId) {
        int numVaccines = 0;
        for (int i = 0; i < acquisitionArr.length; i++) {
            if (acquisitionArr[i].getBrand().getId() == brandId) {
                numVaccines = numVaccines + acquisitionArr[i].getDoses();
            }
        }
        return numVaccines;
    }
    
    
    public int getNumVaccinesAvailable(int brandId) {
        int total = 0;
        for (int i = 0; i < acquisitionArr.length; i++) {
            if (acquisitionArr[i].getBrand().getId() == brandId) {
                if (acquisitionArr[i].getCentre().getId() == -1) {
                    total = total + acquisitionArr[i].getDoses();
                } else {
                    total = total - acquisitionArr[i].getDoses();
                }
            }
        }
        return total;
    }
}
