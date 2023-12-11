

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.time.*;
import java.time.format.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class ExternalHandler {
    public static final String DELIMITER = "#";
    public static final String FILE_NAME = "user_log.txt";
    
    
    public static boolean searchPlace(String coordinates1) {
        boolean searched = true;
        try {
            Desktop desktop = Desktop.getDesktop();
            URI url = new URI("https://www.google.co.za/maps/place/" + coordinates1 + "/");
            desktop.browse(url);
        } catch (Exception e) {
            searched = false;
        }
        return searched;
    }

    public static boolean searchPlace(String coordinates1, String coordinates2) {
        boolean searched = true;
        try {
            Desktop desktop = Desktop.getDesktop();
            URI url = new URI("https://www.google.co.za/maps/dir/" + coordinates1 + "/" + coordinates2 + "/");
            desktop.browse(url);
        } catch (Exception e) {
            searched = false;
        }
        return searched;
    }
    
    public static String readUserMessages(String username) {
        String messageFinal = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line = reader.readLine();
            String[] messages = line.split("#");
            for (int i = 0; i < messages.length; i = i + 2) {
                if (messages[i].equals(username)) {
                    messageFinal = messages[i + 1] + "\n";
                }
            }
        } catch (IOException ex) {
            
        }
        return messageFinal;
    }
    
    
    
    public static void writeToUserMessages(String id, String message) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm");  
            LocalDateTime now = LocalDateTime.now();  
            String newFileText = reader.readLine() + "#" + id + "#["+dtf.format(now)+"] " + message;

            PrintWriter writer = new PrintWriter("user_log.txt");
            writer.write(newFileText);
            writer.flush();
            writer.close();
        } catch (Exception e) {

        }
    }
    
    public static void openUserGuide() {
        try {

            File pdfFile = new File("userguide.pdf");
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
          ex.printStackTrace();
        }
    }
    public static String getTextFileString(Household household){
        String toReturn = "" + 
                household.getId() + DELIMITER
                + household.getOccupants() + DELIMITER
                + household.getStreetAddress() + DELIMITER 
                + household.getZipCode() + DELIMITER 
                + household.getLatitude() + DELIMITER 
                + household.getLongitude();
        System.out.println(toReturn + "gwhbjnK      " + household.getId());
        return toReturn;
    }
    
    
    public static String getTextFileString(Citizen citizen) {
        String toReturn = 
                citizen.getId() + DELIMITER 
                + citizen.getPersonalId() + DELIMITER 
                + citizen.getFirstName()+ DELIMITER 
                + citizen.getLastName() + DELIMITER 
                + citizen.getIsVaccinated() + DELIMITER 
                + citizen.getContactNumber() + DELIMITER 
                + citizen.getRiskLevelNum() + DELIMITER 
                + citizen.getHousehold().getId();
        System.out.println(toReturn + "gwhbjnK      " + citizen.getId());
        return toReturn;
    }
    
    public static String getTextFileString (User user) {
        String toReturn = 
                user.getUsername() + DELIMITER
                + user.getPassword() + DELIMITER
                + user.isIsAdmin() + DELIMITER
                + user.getEmail() + DELIMITER
                + user.getId();
        System.out.println(toReturn + "gwhbjnK      " + user.getId());
        return toReturn;
    }
    
    public static String getTextFileString(Centre centre){
        String toReturn = 
                centre.getId() + DELIMITER 
                + centre.getName() + DELIMITER 
                + centre.getStreetAddress() + DELIMITER 
                + centre.getZipCode() + DELIMITER 
                + centre.getLatitude() + DELIMITER 
                + centre.getLongitude() + DELIMITER 
                + centre.getVaccinesReceived() + DELIMITER 
                + centre.getVaccinesAdministered();
        System.out.println(toReturn + "gwhbjnK      " + centre.getId());
        return toReturn;
    }
    
    public static String getTextFileString(Brand brand){
        String toReturn = 
                brand.getId() + DELIMITER
                + brand.getName() + DELIMITER
                + brand.getCommonName() + DELIMITER
                + brand.getEfficacyRate() + DELIMITER
                + brand.getCountryOfOrigin() + DELIMITER
                + brand.getSupplyReceived() + DELIMITER
                + brand.getSupplyAdministered();
        System.out.println(toReturn + "gwhbjnK      " + brand.getId());
        return toReturn;
    }
    
    public static String getTextFileString(Acquisition acquisition){
        String toReturn = ""
                + acquisition.getId() + DELIMITER
                + acquisition.getDoses() + DELIMITER
                + acquisition.getBrand().getId() + DELIMITER
                + acquisition.getCentre().getId();
        System.out.println(toReturn + "gwhbjnK      " + acquisition.getId());
        return toReturn;
    }
    
    public static String getTextFileString(Vaccination vaccination){
        String toReturn = 
                vaccination.getId()  + DELIMITER
                + vaccination.getDate() + DELIMITER
                + vaccination.getCentre().getId() + DELIMITER
                + vaccination.getCitizen().getId() + DELIMITER
                + vaccination.getBrand().getId();
        System.out.println(toReturn + "gwhbjnK      " + vaccination.getId());
        return toReturn;
    }
    
    public static void writeHouseholdsToFile(HouseholdHandler household){
        Household[] householdArr = household.getHouseholdArr();
        String toWrite = household.getNextId() + "\n" + ExternalHandler.getTextFileString(householdArr[0]);
        
        for (int i = 1; i < householdArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(householdArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(HouseholdHandler.DATA_SOURCE);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Household");
        }
    }
    
    public static void writeCitizensToFile(CitizenHandler citizen) {
        Citizen[] citizenArr = citizen.getCitizenArr();
        String toWrite = citizen.getNextId() + "\n" + ExternalHandler.getTextFileString(citizenArr[0]);
        for (int i = 1; i < citizenArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(citizenArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(CitizenHandler.DATA_SOURCE_CITIZEN);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Citizen");
        }
    }
    
    public static void writeUserToFile(CitizenHandler citizen){
        User[] userArr = citizen.getUserArr();
        String toWrite = ExternalHandler.getTextFileString(userArr[0]);
        
        for (int i = 1; i < userArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(userArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(CitizenHandler.DATA_SOURCE_USER);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("User");
        }
    }
    
    public static void writeCentreToFile(CentreHandler centre){
        Centre[] centreArr = centre.getCentreArr();
        String toWrite = centre.getNextId() + "\n" + ExternalHandler.getTextFileString(centreArr[0]);
        
        for (int i = 1; i < centreArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(centreArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(CentreHandler.DATA_SOURCE);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Centre");
        }
    }
    
    public static void writeBrandsToFile(BrandHandler brand){
        Brand[] brandArr = brand.getBrandArr();
        String toWrite = brand.getNextId() + "\n" + ExternalHandler.getTextFileString(brandArr[0]);
    
        for (int i = 1; i < brandArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(brandArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(BrandHandler.DATA_SOURCE);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Brand");
        }
    }
    
    public static void writeAcquisitionsToFile(AcquisitionHandler acquisition){
        Acquisition[] acquisitionArr = acquisition.getAcquisitionArr();
        String toWrite = acquisition.getNextId() + "\n" + ExternalHandler.getTextFileString(acquisitionArr[0]);
        
        for (int i = 1; i < acquisitionArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(acquisitionArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(AcquisitionHandler.DATA_SOURCE);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Acquisition");
        }
    }
    
    public static void writeVaccinationsToFile(VaccinationHandler vaccination){
        Vaccination[] vaccinationArr = vaccination.getVaccinationArr();
        String toWrite = vaccination.getNextId() + "\n" + ExternalHandler.getTextFileString(vaccinationArr[0]);
        
        for (int i = 1; i < vaccinationArr.length; i++) {
            toWrite = toWrite + "\n" + ExternalHandler.getTextFileString(vaccinationArr[i]);
        }
        try {
            PrintWriter writer = new PrintWriter(VaccinationHandler.DATA_SOURCE);
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.out.println("Vaccination");
        }
    }
    
    public static void writeDataToFile(HouseholdHandler household, 
            CitizenHandler citizen, 
            CentreHandler centre, 
            BrandHandler brand, 
            AcquisitionHandler acquisition,
            VaccinationHandler vaccination)
    {
        writeHouseholdsToFile(household);
        writeCitizensToFile(citizen);
        writeUserToFile(citizen);
        writeCentreToFile(centre);
        writeBrandsToFile(brand);
        writeAcquisitionsToFile(acquisition);
        writeVaccinationsToFile(vaccination);
    }

}
