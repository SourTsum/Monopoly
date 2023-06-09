import javax.swing.*;
import java.awt.*;

public class RunTime {
    public static void main(String[] args) {
        JFrame MainFrame = new JFrame();
        MainFrame.setBounds(0,0,1280,720);

        String DIR = RunTime.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        DIR = DIR.substring(0,DIR.indexOf("out"));

        // Sets up the background
        JLabel Background = new JLabel(); Background.setBounds(0,-26,1280,720);
        ImageIcon IconBG= new ImageIcon(new ImageIcon(DIR + "resources/MainMenuBG.PNG").getImage().getScaledInstance(MainFrame.getWidth(),MainFrame.getHeight() - 26, Image.SCALE_AREA_AVERAGING));
        Background.setIcon(IconBG);



        // Sets up the hitboxes for buttons
        MainMenu.setDIR(DIR);
        MainMenu.StartMenu(MainFrame,Background);


        MainFrame.add(Background);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setResizable(false);
        MainFrame.setLayout(null);
        MainFrame.setVisible(true);
    }
}
