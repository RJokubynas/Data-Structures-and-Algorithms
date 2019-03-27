public class Car extends Vehicle {  //Update required on this line

    private boolean sportsModel; // check if the vehicle is a Sports Model or no
    
    
    // Instance methods of child
    public void setSportsModel(boolean sports) {
        sportsModel = sports;
    }
    
    public boolean isSportsModel() 
    {
        return sportsModel;
    }
    
    public Car()
    {
        super();
        this.sportsModel = false;
    }
    
    public Car(boolean sportsModel)
    {
        super();
        this.sportsModel = sportsModel;
    }
    
    public Car(int noOfDoors, String brand, boolean sportsModel)
    {
        super(noOfDoors,brand);
        this.sportsModel = sportsModel;
    }
}