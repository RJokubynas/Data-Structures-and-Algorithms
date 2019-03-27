public class SUV extends Vehicle { //Update required on this line
    
    private String wheelDrive; // states whether SUV is 4, front, or rear wheel drive
    
    public SUV()
    {
        super();
        wheelDrive="front";
    }
    
    //Write General Constructor here
    public SUV(int noOfDoors, String brand, String wheelDrive)
    {
        super(noOfDoors, brand);
        this.wheelDrive = wheelDrive;
    }
    
    //Write Getter and Setter methods here
    
    public void setDrive(String wheelDrive)
    {
        this.wheelDrive = wheelDrive;
    }
    
    public String getDrive()
    {
        return wheelDrive;
    }
    
    
}