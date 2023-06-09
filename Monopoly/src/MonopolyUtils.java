import javax.swing.*;

public class MonopolyUtils {
    static ImageIcon[] PropertyIcons = new ImageIcon[36];
    static String[] SubDIR = {
            "1.PNG",
            "CommunityChest.png",
            "2.PNG",
            "IncomeTax.png",
            "3.PNG",
            "4.PNG",
            "ChanceCard.png",
            "5.PNG",
            "6.PNG",
            "7.PNG", // Left Begins
            "8.PNG",
            "9.PNG",
            "10.PNG",
            "11.PNG",
            "12.PNG",
            "CommunityChest.png",
            "13.PNG",
            "14.PNG",
            "15.PNG",
            "ChanceCard.png",
            "16.PNG",
            "17.PNG",
            "18.PNG",
            "19.PNG",
            "20.PNG",
            "21.PNG",
            "22.PNG",
            "23.PNG",
            "24.PNG",
            "CommunityChest.png",
            "25.PNG",
            "26.PNG",
            "ChanceCard.png",
            "27.PNG",
            "IncomeTax.png",
            "28.PNG"

    };
    MonopolyUtils(String DIR){
        for(int i = 0; i < 28; i++){
            PropertyIcons[i] = new ImageIcon(DIR + "resources/property/" + SubDIR[i]);
        }
    }
}
