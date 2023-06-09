import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    //(String DIR, int Location, int Color, int Cost, int HouseCost, int Rent1, int Rent2, int Rent3, int Rent4, int Rent5, int Rent6, int Rent7)
    // colors: brown-1, cyan-2, pink-3, orange-4, red-5, yellow-6, green-7, blue-8
    // static arraylist for storing all the cards
    static ArrayList<PropertyCard> cards = new ArrayList<PropertyCard>();
    public ArrayList<PropertyCard> OwnedCards = new ArrayList<PropertyCard>();
    static ArrayList<Railroad> railroads = new ArrayList<Railroad>();
    JLabel PlayerIcon; JLabel BoardIcon;
    BoardSpace OldSpace;
    static String DIR = "";
    int playerNumber;
    int playerPosition;
    int money = 1500;
    JLabel MoneyLBL;

    void AddMoney(int x){
        money += x;
        MoneyLBL.setText(String.valueOf(x + money));
        MoneyLBL.revalidate();
    }


    void MoveTo(BoardSpace Space){
        if(OldSpace != null){
            if(OldSpace.PlayerStack.size() > 1){
                for(Player plr: OldSpace.PlayerStack){
                    Point LocalPosition = OldSpace.PossiblePositions[OldSpace.PlayerStack.size()];
                    plr.BoardIcon.setBounds((int) LocalPosition.getX(),(int) LocalPosition.getY(),PlayerIcon.getWidth(),PlayerIcon.getHeight());
                    plr.BoardIcon.revalidate();
                }
            }
            OldSpace.PlayerStack.pop();
        }
//        Point LocalPosition = Space.PossiblePositions[Space.PlayerStack.size()];
        Point LocalPosition = Space.SpacePosition;
        BoardIcon.setBounds((int) LocalPosition.getX(),(int) LocalPosition.getY(),PlayerIcon.getWidth(),PlayerIcon.getHeight());
        Space.PlayerStack.add(this);
        BoardIcon.revalidate();
        OldSpace = Space;
    }



    Player(JLabel PlayerICON,String DIR, int playerNumber){
        this.BoardIcon = new JLabel();
        BoardIcon.setBounds(10,10,20,20);
        BoardIcon.setIcon(new ImageIcon(((ImageIcon) PlayerICON.getIcon()).getImage().getScaledInstance(20,20,Image.SCALE_FAST)));



        this.PlayerIcon = PlayerICON;
        this.DIR = DIR;
        this.playerNumber = playerNumber;
        this.playerPosition = 0;
        this.money = 1500;
    }




    // declaring all the cards
    public static void declareCards() {
        cards.add(new PropertyCard(DIR, 1, 1, 60, 50, 2, 10, 30, 90, 160, 250, 750));
        cards.add(new PropertyCard(DIR, 3, 1, 60, 50, 4, 20, 60, 180, 320, 450, 90));
        cards.add(new PropertyCard(DIR, 6, 2, 100, 50, 6, 30, 90, 270, 400, 550, 1050));
        cards.add(new PropertyCard(DIR, 8, 2, 100, 50, 6, 30, 90, 270, 400, 550, 1050));
        cards.add(new PropertyCard(DIR, 9, 2, 120, 50, 8, 40, 100, 300, 450, 600, 1100));
        cards.add(new PropertyCard(DIR, 11, 3, 140, 100, 10, 50, 150, 450, 625, 750, 1250));
        cards.add(new PropertyCard(DIR, 13, 3, 140, 100, 10, 50, 150, 450, 625, 750, 1250));
        cards.add(new PropertyCard(DIR, 14, 3, 160, 100, 12, 60, 180, 500, 700, 900, 1400));
        cards.add(new PropertyCard(DIR, 16, 4, 180, 100, 14, 70, 200, 550, 750, 950, 1450));
        cards.add(new PropertyCard(DIR, 18, 4, 180, 100, 14, 70, 200, 550, 750, 950, 1450));
        cards.add(new PropertyCard(DIR, 19, 4, 200, 100, 16, 80, 220, 600, 800, 1000, 1500));
        cards.add(new PropertyCard(DIR, 21, 5, 220, 150, 18, 90, 250, 700, 875, 1050, 2050));
        cards.add(new PropertyCard(DIR, 23, 5, 220, 150, 18, 90, 250, 700, 875, 1050, 2050));
        cards.add(new PropertyCard(DIR, 24, 5, 240, 150, 20, 100, 300, 750, 925, 1100, 2100));
        cards.add(new PropertyCard(DIR, 26, 6, 260, 150, 22, 110, 330, 800, 975, 1150, 2150));
        cards.add(new PropertyCard(DIR, 27, 6, 260, 150, 22, 110, 330, 800, 975, 1150, 2150));
        cards.add(new PropertyCard(DIR, 29, 6, 280, 150, 24, 120, 350, 850, 1025, 1200, 2200));
        cards.add(new PropertyCard(DIR, 31, 7, 300, 200, 26, 130, 390, 900, 1100, 1275, 2275));
        cards.add(new PropertyCard(DIR, 32, 7, 300, 200, 26, 130, 390, 900, 1100, 1275, 2275));
        cards.add(new PropertyCard(DIR, 34, 7, 320, 200, 28, 150, 450, 1000, 1200, 1400, 2400));
        cards.add(new PropertyCard(DIR, 37, 8, 350, 200, 35, 175, 500, 1100, 1300, 1500, 2500));
        cards.add(new PropertyCard(DIR, 39, 8, 400, 200, 50, 200, 600, 1400, 1700, 2000, 3000));
    }


    // static arraylist for railroads
//    static ArrayList<Railroad> railroads = new ArrayList<Railroad>();

    // static arraylist for chances and chests
//    static ArrayList<Lucky> luckies = new ArrayList<Lucky>();



    // returns the arraylist of cards for the player number
    public ArrayList<PropertyCard> getCardList() {
        ArrayList<PropertyCard> list = new ArrayList<PropertyCard>();
        for(int i=0; i<cards.size(); i++) {
            if(cards.get(i).Owner == playerNumber)
                list.add(cards.get(i));
        }
        return list;
    }

    // checking if the player whose railroad you are on owns other railroads
    // input: railroad location. output: rent paid on railroad
    public int rent(int o) {
        int k=0;
        for(int i=0; i<railroads.size(); i++) {
            if(railroads.get(i).owner == o)
                k++;
        }
        return k*50;
    }

    // returns the position of a card with a certain location in the arraylist
    //input: board coordinate, output: arraylist position
    public int getPosition(int l) {
        for(int i=0; i<cards.size(); i++) {
            if(cards.get(i).Location == l)
                return i;
        }
        return -1;
    }

}




