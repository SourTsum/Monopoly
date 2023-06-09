import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Die {
    ArrayList<ImageIcon> DieSides = new ArrayList<ImageIcon>();
    JLabel DieLBL = new JLabel();
    Die(String DIR){
        for(int i = 0 ; i < 6; i++){
            DieSides.add(
                    new ImageIcon(
                            new ImageIcon(DIR + "resources/dice/dice" + (i + 1) + ".PNG").getImage().getScaledInstance(50,50, Image.SCALE_FAST)
                    )
            );
        }
        DieLBL.setIcon(DieSides.get(0));
    }
}
