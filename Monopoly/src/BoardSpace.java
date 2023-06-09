import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class BoardSpace {
    Point SpacePosition;
    Point[] PossiblePositions = new Point[4];
    Stack<Player> PlayerStack = new Stack<Player>();
    JLabel HitBox = new JLabel();

    BoardSpace(char Direction, int x, int y, JPanel Frame){
        if(Direction == 'B' || Direction == 'T'){
            HitBox.setBounds(x,y,53,66);
            SpacePosition = new Point(HitBox.getX(),HitBox.getY());
            int index = 0;
            for(int i = 0 ; i < 2; i++){
                for(int j = 0; j < 2; j++){
                    PossiblePositions[index] = new Point((int) SpacePosition.getX() + 4 + (i * 25),(int) SpacePosition.getY() - 10 + (j * 25));
                    index += 1;
                }
            }
        }
        if(Direction == 'R' || Direction == 'L'){
            HitBox.setBounds(x,y,66,53);
            SpacePosition = new Point(HitBox.getX(),HitBox.getY());
            int index = 0;
            for(int i = 0 ; i < 2; i++){
                for(int j = 0; j < 2; j++){
                    PossiblePositions[index] = new Point((int) SpacePosition.getY() - 2 + (j * 25),(int) SpacePosition.getX() + 40 + (i * 25));
                    index += 1;
                }
            }
        }
        //TODO: Remove DEBUGING
//        HitBox.setBackground(Color.GREEN);
//        HitBox.setOpaque(true);
        HitBox.setVisible(true);
        Frame.add(HitBox);
    }
}
