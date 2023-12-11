


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
 */
public class CitizenHandler {
    private Citizen[] citizenArr;
    private Citizen[] vaccinatedCitizenArr;
    private Citizen[] notVaccinatedCitizenArr;
    private User[] userArr;
    private User currentUser;
    private int nextId;

    public static final String DATA_SOURCE_CITIZEN = "citizen.txt";
    public static final String DATA_SOURCE_USER = "user.txt";

    public CitizenHandler(HouseholdHandler householdHandler, int currentUserID) {
        try {
           
           
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE_CITIZEN));
            String line = reader.readLine();
            line = reader.readLine();
            
            int count = 0;
            int numVaccinated = 0;
            while (line!=null) {
                
                if ((line.split(ExternalHandler.DELIMITER)[4]).equals("true")) {
                    numVaccinated++;
                }
                count++;
                line = reader.readLine();
            }
            
            citizenArr = new Citizen[count];
            vaccinatedCitizenArr = new Citizen[numVaccinated];
            notVaccinatedCitizenArr = new Citizen[count - numVaccinated]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE_CITIZEN));
            
            count = 0;
            numVaccinated = 0;
            line = reader.readLine();
            nextId = Integer.parseInt(line);
            
            for (int i = 0; i < citizenArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                int id = Integer.parseInt(fields[0]);
                String personalId = fields[1];
                String firstName = fields[2];
                String lastName = fields[3];
                boolean isVaccinated = Boolean.parseBoolean(fields[4]);
                String contactNumber = fields[5];
                int riskLevel = (Integer.parseInt(fields[6]));
                int householdId = Integer.parseInt(fields[7]);
                Household currentHousehold = householdHandler.findHousehold(householdId);
                citizenArr[count] = new Citizen(id, personalId, firstName, lastName, isVaccinated, contactNumber, riskLevel, currentHousehold);
                
                
                if (isVaccinated) {
                    vaccinatedCitizenArr[numVaccinated] = citizenArr[count];
                    numVaccinated++;
                } else {
                    notVaccinatedCitizenArr[count - numVaccinated] = citizenArr[count];
                }
                
                count++;
                
            }

        } catch (Exception e) {
            citizenArr = null;
            vaccinatedCitizenArr = null;
            notVaccinatedCitizenArr = null;
        }
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE_USER));
            String line = reader.readLine();
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            userArr = new User[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE_USER));
            count = 0;
            
            for (int i = 0; i < userArr.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                
                String username = fields[0];
                String password = fields[1];
                boolean isAdmin = Boolean.parseBoolean(fields[2]);
                String email = fields[3];
                int num = Integer.parseInt(fields[4]);
                
                Citizen person = citizenArr[findCitizenPos(num,citizenArr)];
                userArr[count] = new User(person, password, isAdmin, username, email);
                if (num == currentUserID) {
                    currentUser = userArr[count];
                }
                count++;
            }
            
            sortCitizenArr();

        } catch (Exception e) {
            userArr = null;
        }
        
    }

    public Citizen[] getCitizenArr() {
        return citizenArr;
    }

    public void setCitizenArr(Citizen[] citizenArr) {
        this.citizenArr = citizenArr;
    }
    
    

    public Citizen[] getVaccinatedCitizenArr() {
        return vaccinatedCitizenArr;
    }

    public Citizen[] getNotVaccinatedCitizenArr() {
        return notVaccinatedCitizenArr;
    }

    public User[] getUserArr() {
        return userArr;
    }

    public void setUserArr(User[] userArr) {
        this.userArr = userArr;
    }
    
    

    public User getCurrentUser() {
        return currentUser;
    }
    
    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }
    
    public static int findCitizenPos(int soughtId, Citizen[] citizenArr) {
        int position = -1;
        for (int i = 0; i < citizenArr.length; i++) {
            if (citizenArr[i].getId() == soughtId) {
                position = i;
            }
        }

        return position;
    }
    
    public static int findUserPos(int soughtId, User[] userArr) {
        
        int position = -1;
        for (int i = 0; i < userArr.length; i++) {
            if (userArr[i].getId() == soughtId) {
                position = i;
            }
        }

        return position;
    }
    
    public void sortCitizenArr(){
        for (int i = 0; i < citizenArr.length; i++) {
            for (int j = 0; j < citizenArr.length - 1; j++) {
                if (citizenArr[j].getFirstName().compareTo(citizenArr[j + 1].getFirstName()) > 0) {
                    Citizen copy = citizenArr[j];
                    citizenArr[j] = citizenArr[j + 1];
                    citizenArr[j + 1] = copy;
                }
            }
        }
    
    }
    
    public static int calcOccupants(int soughtId, Citizen[] citizenArr) {
        
        int numOccupants = 0;
        for (int i = 0; i < citizenArr.length; i++) {
            if (citizenArr[i].getHousehold().getId() == soughtId) {
                numOccupants++;
            }
        }
        return numOccupants;
    }
    
    public int[] findNumZipCode(String zipCode, int riskLevel) {
        int numCitizens = 0;
        int numVaccinatedCitizen = 0;
        int[] ratio = new int[2];
        for (int i = 0; i < citizenArr.length; i++) {
            if (citizenArr[i].getRiskLevelNum() == riskLevel) {
                if (zipCode.equals(citizenArr[i].getHousehold().getZipCode())) {
                    numCitizens++;
                    if (citizenArr[i].getIsVaccinated()) {
                        numVaccinatedCitizen++;
                    }
                }
            }
        }
        ratio[0] = numVaccinatedCitizen;
        ratio[1] = numCitizens;
        return ratio;
    }
    
    public static String luhnAlgorithm(String idNum) {
        int toReturn = 0;
        if (idNum.length() == 12) {
            int sum = 0;

            for (int i = 0; i < idNum.length(); i++) {
                if (i % 2 == 0) {
                    sum = sum + (Integer.parseInt("" + idNum.charAt(i)));
                } else {
                    String num = "" + ((Integer.parseInt("" + idNum.charAt(i))) * 2);
                    if (num.length() > 1) {
                        sum = sum + 1 + Integer.parseInt("" + num.charAt(1));
                    } else {
                        sum = sum + Integer.parseInt(num);
                    }
                }
            }
            toReturn = 10 - (sum % 10);
            if (toReturn == 10) {
                toReturn = 0;
            }
        }
        idNum = idNum + toReturn;
        return idNum;
    }
    
    public static User[] getSafeUsers(){
        User[] usernames = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_SOURCE_USER));
            String line = reader.readLine();
            int count = 0;
            
            while (line!=null){
                count++;
                line = reader.readLine();
            }

            usernames = new User[count]; 
            reader = new BufferedReader(new FileReader(DATA_SOURCE_USER));
            count = 0;
            
            for (int i = 0; i < usernames.length; i++) {
                line = reader.readLine();
                String[] fields = line.split(ExternalHandler.DELIMITER);
                
                usernames[count] = new User();
                usernames[count].setUsername(fields[0]);
                usernames[count].setPassword(fields[1]);
                usernames[count].setId(Integer.parseInt(fields[4]));
                count++;
            }
            for (int i = 0; i < usernames.length; i++) {
                System.out.println(usernames[i].getUsername());
                
            }
        
        } catch (Exception e) {
            
        }
        return usernames;
    }
    
}
