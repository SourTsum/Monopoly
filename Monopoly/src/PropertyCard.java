import javax.swing.*;

public class PropertyCard {
    public int Location;
    public int Color;
    public int Cost;
    public int HouseCost;
    public int[] Rent;
    public int House;
    public int Owner;


    // colors: brown-1, cyan-2, pink-3, orange-4, red-5, yellow-6, green-7, blue-8
    // owners: noone-0, 1, 2, 3, 4

    private String DIR;
    JLabel PropertyLBL = new JLabel();

    private MonopolyUtils INIT;

    PropertyCard(String DIR){
        this.DIR = DIR;
        new MonopolyUtils(DIR);
        ImageIcon PROPERTY_ICON = INIT.PropertyIcons[0];
        PropertyLBL.setIcon(PROPERTY_ICON);
    }

    PropertyCard(String DIR, int Location, int Color, int Cost, int HouseCost, int Rent1, int Rent2, int Rent3, int Rent4, int Rent5, int Rent6, int Rent7){
        this.DIR = DIR;
        new MonopolyUtils(DIR);
        ImageIcon PROPERTY_ICON = INIT.PropertyIcons[0];
        PropertyLBL.setIcon(PROPERTY_ICON);

        this.Location = Location;
        this.Color = Color;
        this.Cost = Cost;
        this.HouseCost = HouseCost;
        this.Rent = new int[]{Rent1, Rent2, Rent3, Rent4, Rent5, Rent6, Rent7};
        this.House = 0;
        this.Owner = 0;
    }

    // adds houses.
    // returns -1 if too many houses
    // Input: how many houses. Output: cost of adding houses.
    public int addHouse(int i) {
        if(House+i > 6)
            return -1;

        House += i;
        return i*HouseCost;
    }

    // returns rent amount
    //input: location. output: rent amount
     public int rent() {
        return  Rent[House];
     }


    // buy house
    // input: owner's number. Sets the owner
    public void buyHouse(int i) {
        Owner = i;
    }

}

