/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author thandeka.matrose
 */
public class Citizen {
    private int id = 0;
    private String personalId = "";
    private String firstName = "NAME NOT PROVIDED";
    private String lastName = "";
    private boolean isVaccinated = false;
    private String contactNumber = "+0-000-000-0000";
    private int riskLevel = 4;
    private Household household = new Household();
    
    public static final int PHASE_ONE = 1;
    public static final int PHASE_TWO = 2;
    public static final int PHASE_THREE = 3;
    public static final int PHASE_FOUR = 4;
    public static final String[] RISK_ARRAY = {"Phase One", "Phase Two", "Phase Three", "Phase Four"};
    

    public Citizen(int newId, String newPersonalId, String newFirstName, String newLastName, boolean newIsVaccinated , String newContactNumber, int newRiskLevel, Household newHousehold) {
        setId(newId);
        setPersonalId(newPersonalId);
        setFirstName(newFirstName);
        setLastName(newLastName);
        setContactNumber(newContactNumber);
        setIsVaccinated(newIsVaccinated);
        setRiskLevel(newRiskLevel);
        setHousehold(newHousehold);
    }
    
    public Citizen(){
        
        id = 0;
        personalId = "";
        firstName = "NAME NOT PROVIDED";
        lastName = "";
        isVaccinated = false;
        contactNumber = "+0-000-000-0000";
        riskLevel = 4;
        household = new Household();
    }
    

    public int getId() { //takes in nothing - does nothing - returns id
        return id;
    }
    
    public void setId(int newId) { //takes in newid - modifies id to be newid - returns nothing
        if (newId>-1) {
            this.id = newId;
        }
    }
    public String getPersonalId() { //takes in nothing - does nothing - returns 
        return this.personalId;
    }

    public void setPersonalId(String citizenId) {
        if (citizenId.length()==13) {
            try {
                Long.parseLong(citizenId);
                if (isValidDate(citizenId.substring(0,6))){
                    if (citizenId.charAt(10)=='0'|| citizenId.charAt(10)=='1'){
                        if (validLuhnAlgorithm(citizenId)){
                            this.personalId = citizenId;
                        }
                    }
                }
                
            } catch (NumberFormatException nfe) {
            }
               
        }
        personalId = citizenId;
    }
    public static boolean validLuhnAlgorithm(String idNum){
        int nDigits = idNum.length();

	int nSum = 0;
	boolean isSecond = false;
	for (int i = nDigits - 1; i >= 0; i--)
	{

		int d = idNum.charAt(i) - '0';

		if (isSecond == true)
			d = d * 2;

		// We add two digits to handle
		// cases that make two digits
		// after doubling
		nSum += d / 10;
		nSum += d % 10;

		isSecond = !isSecond;
	}
	return (nSum % 10 == 0);
    }
    
    public int getAge(){
        int age = 0;
        int year = Integer.parseInt(this.getPersonalId().substring(0, 2));
        if (year<=21){
            age = (21 - year);
        } else {
            age = (100-year+21);
        }
        return age;
    }
    
    public boolean isValidDate(String date) {
        boolean isValidDate = false;
        if (date.length()==6) {
            try {
                Integer.parseInt(date);
                
                int month = Integer.parseInt(date.substring(2,4));
                if (month<=13){
                    int dayOfMonth = Integer.parseInt(date.substring(4,6));
                    if (month==4||month==6||month==9||month==11){
                        if (dayOfMonth<=30){
                            isValidDate = true;
                        }
                    } else if (month==2){
                        if (Integer.parseInt(date.substring(0,2))%4==0){
                            if (dayOfMonth<=29) {
                                isValidDate = true;
                            }
                        } else {
                            if (dayOfMonth<=28) {
                                isValidDate = true;
                            }
                        }
                    } else {
                        if (dayOfMonth<=30) {
                            isValidDate = true;
                        }
                    }
                }
            } catch (NumberFormatException nfe) {
                
            }
               
        }
        return isValidDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        if (newFirstName.length()>0){
            firstName = newFirstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        if (newLastName.length()>0){
            lastName = newLastName;
        }
    }

     public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(boolean newIsVaccinated) {
        isVaccinated = newIsVaccinated;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String newContactNumber) {
        if (newContactNumber.length()>8){
            contactNumber = newContactNumber;
        }             
    }

    public String getRiskLevelString() {
        return RISK_ARRAY[riskLevel-1];
    }
    
    public int getRiskLevelNum() {
        return this.riskLevel;
    }

    public void setRiskLevel(int newRiskLevel) {
        if (newRiskLevel<=4 && newRiskLevel>=1){
            riskLevel = newRiskLevel;
        }
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household newHousehold) {
        /*
        this.household.setStreetAddress(household.getStreetAddress());
        this.household.setZipCode(household.getZipCode());
        this.household.setId(household.getId());
        this.household.setOccupants(household.getOccupants());
        this.household.setCoordinates(household.getCoordinates());*/
        household = newHousehold;
    }

    @Override
    public String toString() {
        return firstName + " "+ lastName;
    }
    
}
