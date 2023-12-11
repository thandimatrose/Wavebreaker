/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thandeka.matrose
*/
public class Acquisition {
    private int id = -1;
    private int doses = 0;
    private Brand brand = new Brand();
    private Centre centre = new Centre();
    
    public Acquisition() {
        
        
    }
    
    public Acquisition(int newId, int newDoses, Brand newBrand, Centre newCentre) {
        setId(newId);
        setDoses(newDoses);
        setBrand(newBrand);
        setCentre(newCentre);
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        if (newId>=-1) {
            id = newId;
        }
    }
    
    public int getDoses() {
        return doses;
    }

    public void setDoses(int newDoses) {
        if (newDoses>0){
            doses = newDoses;
        }
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand newBrand) {
        brand = newBrand;
    }
    
    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
    
    
}
