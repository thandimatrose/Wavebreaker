

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
public class BrandHandler {
    
    private Brand[] brandArr;
    private int nextId;
    
    public static final String DATA_SOURCE = "brand.txt";

    public BrandHandler() {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            brandArr = new Brand[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE));
            count = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            
            for (int i = 0; i < brandArr.length; i++) {
                
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String commonName = fields[2];
                double efficacyRate = (Double.parseDouble(fields[3]));
                String countryOfOrigin = fields[4];
                int supplyReceived = Integer.parseInt(fields[5]);
                int supplyAdministered = Integer.parseInt(fields[6]);
                brandArr[count] = new Brand();
                brandArr[count].setId(id);
                brandArr[count].setName(name);
                brandArr[count].setCommonName(commonName);
                brandArr[count].setEfficacyRate(efficacyRate);
                brandArr[count].setCountryOfOrigin(countryOfOrigin);
                brandArr[count].setSupplyReceived(supplyReceived);
                brandArr[count].setSupplyAdministered(supplyAdministered);
                
                count++;
            }

        } catch (Exception e) {
            
            

        }
    }

    public Brand[] getBrandArr() {
        return brandArr;
    }

    public void setBrandArr(Brand[] newBrandArr) {
        brandArr = newBrandArr;
    }
    
    
    
    public int getNextId() {
        return nextId;
    }

    public void setNextId(int newNextId) {
        nextId = newNextId;
    }
    
    public static Brand findBrand(int soughtId, Brand[] brandArr) {
        Brand position = null;
        for (int i = 0; i < brandArr.length; i++) {

            if (brandArr[i].getId() == soughtId) {
                position = brandArr[i];
            }
        }

        return position;
    }
    
    public static int findBrandPos(int soughtId, Brand[] brandArr) {
        int position = -1;
        for (int i = 0; i < brandArr.length; i++) {
            if (brandArr[i].getId() == soughtId) {
                position = i;
            }
        }

        return position;
    }
    
    
}
