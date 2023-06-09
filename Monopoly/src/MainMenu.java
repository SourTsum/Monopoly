import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainMenu {
    private static String DIR;  // Directory path
    private static ArrayList<Player> PlayerList = new ArrayList<>();  // ArrayList to store player objects
    private static BoardSpace[] BoardSpaces = new BoardSpace[36];
    private static int PlayerTurn = 0;
    private static final String[] IMG_DIR = {"Thimble.png", "Iron.png", "Hat.png", "Boot.png"};  // Array of image file names



    // Method to set the directory path
    static void setDIR(String localDIR) {
        DIR = localDIR;
    }

    // Method to create and display the start menu
    static void StartMenu(JFrame RunFrame, JLabel Background) {
        // Set the background image
        ImageIcon IconBG = new ImageIcon(new ImageIcon(DIR + "resources/MainMenuBG.PNG").getImage().getScaledInstance(RunFrame.getWidth(), RunFrame.getHeight() - 26, Image.SCALE_AREA_AVERAGING));
        Background.setIcon(IconBG);
        RunFrame.revalidate();

        JPanel contentPane = (JPanel) RunFrame.getContentPane();
        JLabel[] HitBoxes = new JLabel[5];  // Array of labels representing hit boxes for menu options
        // Array of button IDs

        // Create and configure the hit boxes
        for (int i = 0; i < HitBoxes.length; i++) {
            HitBoxes[i] = new JLabel();

            if (i != 4) {
                HitBoxes[i].setBounds(217 + (i * 220), 440, 190, 55);
            } else {
                HitBoxes[i].setBounds(485, 340, 315, 55);
            }

            int localIndex = i;

            // Add mouse listener to handle mouse events on the hit boxes
            HitBoxes[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (localIndex == 3) {
                        System.exit(0);  // Exit the program if "Exit" is clicked
                    } else {
                        if (localIndex == 4) {
                            NewGameMenu(RunFrame, Background);  // Open the new game menu if "NewGame" is clicked
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
            });

            contentPane.add(HitBoxes[i]);  // Add the hit boxes to the content pane
        }
    }

    // Method to create and display the new game menu
    static void NewGameMenu(JFrame RunFrame, JLabel Background) {
        RunFrame.getContentPane().removeAll();  // Remove all existing components

        // Set the background image
        ImageIcon IconBG = new ImageIcon(new ImageIcon(DIR + "resources/CharactersSelection.png").getImage().getScaledInstance(RunFrame.getWidth(), RunFrame.getHeight() - 26, Image.SCALE_AREA_AVERAGING));
        Background.setIcon(IconBG);

        JPanel contentPane = (JPanel) RunFrame.getContentPane();

        JButton PlayBTN = new JButton();  // Button to start the game
        PlayBTN.setBounds(525, 550, 225, 75);
        ImageIcon PLAY_ICON = new ImageIcon(DIR + "resources/playBTN.png");
        PlayBTN.setIcon(PLAY_ICON);

        JButton[] PlayerSelect = new JButton[4];  // Array of buttons for player selection
        JLabel[] PlayerIcons = new JLabel[4];  // Array of labels to display player icons

        // Add action listener to the Play button
        PlayBTN.addActionListener(e -> {
            int count = 0;
            boolean accepted = false;
            for (Player plr : PlayerList) {
                if (plr != null) {
                    count++;
                }
                if (count >= 2) {
                    accepted = true;
                    break;
                }
            }
            if (accepted) {
                for (JButton btn : PlayerSelect) {
                    contentPane.remove(btn);
                }
                for (JLabel lbl : PlayerIcons) {
                    contentPane.remove(lbl);
                }
                contentPane.remove(PlayBTN);

                MainGameMenu(contentPane, Background);  // Open the main game menu
            }
        });

        contentPane.add(PlayBTN);

        // Create player selection buttons and icons
        for (int i = 0; i < 4; i++) {
            ImageIcon LocalPlayerIcon = new ImageIcon(new ImageIcon(DIR + "resources/" + IMG_DIR[i]).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING));
            PlayerIcons[i] = new JLabel();
            PlayerIcons[i].setIcon(LocalPlayerIcon);
            PlayerIcons[i].setBounds(285 + (200 * i), 325, LocalPlayerIcon.getIconWidth(), LocalPlayerIcon.getIconHeight());

            contentPane.add(PlayerIcons[i]);  // Add player icons to the content pane
        }

        ImageIcon PlayerIcon = new ImageIcon(new ImageIcon(DIR + "resources/AddPlayer.png").getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING));
        for (int i = 0; i < 4; i++) {
            PlayerList.add(null);

            PlayerSelect[i] = new JButton();
            PlayerSelect[i].setBorderPainted(false);
            PlayerSelect[i].setBackground(Color.WHITE);
            PlayerSelect[i].setIcon(PlayerIcon);
            PlayerSelect[i].setBounds(275 + (200 * i), 120, 100, 100);

            PlayerSelect[i].setBackground(Color.WHITE);
            PlayerSelect[i].setOpaque(true);

            int index = i;

            // Add mouse listener to handle player selection
            PlayerSelect[i].addMouseListener(new MouseListener() {
                boolean Selected = false;

                @Override
                public void mouseClicked(MouseEvent e) {
                    contentPane.revalidate();
                    Selected = !Selected;
                    if (!Selected) {
                        PlayerList.set(index, new Player(PlayerIcons[index],DIR + "resources/" + IMG_DIR[index],1));
                        PlayerIcons[index].setBackground(Color.GRAY);
                        PlayerSelect[index].setIcon(PlayerIcons[index].getIcon());
                        PlayerIcons[index].setVisible(false);
                    } else {
                        PlayerList.set(index, null);
                        PlayerIcons[index].setVisible(true);
                        PlayerSelect[index].setIcon(PlayerIcon);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            contentPane.add(PlayerSelect[i]);  // Add player selection buttons to the content pane
        }

        contentPane.remove(Background);
        contentPane.add(Background);
        contentPane.revalidate();
        contentPane.repaint();
    }

    // Method to create and display the main game menu
    static void MainGameMenu(JPanel contentPane, JLabel Background) {
        contentPane.removeAll();  // Remove all existing components
        int C = -50;
        Background.setIcon(new ImageIcon(new ImageIcon(DIR + "resources/Board.PNG").getImage().getScaledInstance(720 + C, 720 + C, Image.SCALE_AREA_AVERAGING)));
        Background.setBounds(0, 5, 720 + C, 720 + C);

        PlayerList = RemoveNULL(PlayerList);
        SetupGameComponents(contentPane);
        SetupPlayers(contentPane);



        contentPane.add(Background);
        contentPane.revalidate();
        contentPane.repaint();
    }
    static void SetupPlayers(JPanel contentPane){
        for(int i = 0 ; i < PlayerList.size(); i++){
            Player local = PlayerList.get(i);
            JLabel localLBL = local.PlayerIcon;
            localLBL.setIcon(new ImageIcon(new ImageIcon(DIR + "resources/" + IMG_DIR[i]).getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING)));
            localLBL.setBounds(680, (100 * i) + 15, localLBL.getWidth(), localLBL.getHeight());
            contentPane.add(localLBL);
            localLBL.setVisible(true);
            localLBL.revalidate();
        }

        JLabel[] MONEY_LBLS = new JLabel[PlayerList.size()];
        for(int i = 0; i < PlayerList.size(); i++){
            JLabel localICON = PlayerList.get(i).PlayerIcon;
            MONEY_LBLS[i] = new JLabel("$1500");
            PlayerList.get(i).MoneyLBL = MONEY_LBLS[i];
            MONEY_LBLS[i].setBounds(localICON.getX() + 8, (100 * i) + 65,100,20);
            contentPane.add(MONEY_LBLS[i]);
        }
        for(int i = 0; i < PlayerList.size(); i++){
            contentPane.add(PlayerList.get(i).BoardIcon);
        }
        contentPane.revalidate(); contentPane.repaint();
    }
    static void SetupGameComponents(JPanel contentPane){
        Die[] Dice = {new Die(DIR), new Die(DIR)};
        JLabel localLBL;
        int CY = 60;
        for(int i = 0; i < 2; i++){
            localLBL = Dice[i].DieLBL;
            contentPane.add(localLBL);
            localLBL.setBounds(1155,(i * 75) + 365 + CY,200,200);
            localLBL.setVisible(true);
            localLBL.revalidate(); localLBL.repaint();
        }

        PropertyCard LandedCard = new PropertyCard(DIR);

        LandedCard.PropertyLBL.setBounds(840,220 + CY,500,500);
        LandedCard.PropertyLBL.setVisible(true);
        contentPane.add(LandedCard.PropertyLBL);

        for(Player plr: PlayerList){
            plr.PlayerIcon.setBounds(plr.PlayerIcon.getX(),plr.PlayerIcon.getY(),53,53);
        }

        SetupBoard(contentPane);


        JButton RollDice = new JButton("ROLL");
        RollDice.addActionListener(e -> {
            RollDice.setVisible(false);
            RollDice(Dice,RollDice,LandedCard);
        });

        RollDice.setBounds(Dice[0].DieLBL.getX() - 25,Dice[0].DieLBL.getY() - 5,100,50);
        contentPane.add(RollDice);


        JButton BuyBTN = new JButton("BUY");
        BuyBTN.setVisible(true);
        BuyBTN.setBounds(710,350 + CY,100,30);

        contentPane.add(BuyBTN);

        contentPane.revalidate();contentPane.repaint();
    }
    static void SetupBoard(JPanel contentPane){
        for(int i = 0; i < 9; i++){
            BoardSpaces[i] = (new BoardSpace('B',527 - (i * 55),608,contentPane));
        }
        for(int i = 0; i < 9; i++){
            BoardSpaces[9 + i] = (new BoardSpace('L',0,533 - (i * 55),contentPane));
        }
        for(int i = 0; i < 9; i++){
            BoardSpaces[18 + i] = (new BoardSpace('T',88 + (i * 55),5,contentPane));
        }
        for(int i = 0; i < 9; i++){
            BoardSpaces[27 + i] = (new BoardSpace('R',602,93 + (i * 55),contentPane));
        }
    }

    // TODO: Come back to edit the RollDice Buttons availability
    static void RollDice(Die[] Dice, JButton RollDice, PropertyCard RolledCard){
        Timer timer = new Timer(100, new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] DiceValues = {(int) (Math.random() * Dice[0].DieSides.size()),(int) (Math.random() * Dice[1].DieSides.size())};

                Dice[0].DieLBL.setIcon(Dice[0].DieSides.get(DiceValues[0]));
                Dice[1].DieLBL.setIcon(Dice[1].DieSides.get(DiceValues[1]));
                if (count >= 25) {
                    Timer timer = (Timer) e.getSource();
                    RollDice.setVisible(true);


                    PlayerList.get((PlayerTurn + 1) % PlayerList.size()).playerPosition += DiceValues[0] + DiceValues[1];
                    // Position
                    int position = PlayerList.get((PlayerTurn + 1) % PlayerList.size()).playerPosition + 1;
                    if(position == 0){
                        PlayerList.get((PlayerTurn + 1) % PlayerList.size()).playerPosition = 0;
                        PlayerList.get((PlayerTurn + 1) % PlayerList.size()).AddMoney(200);
                    }

                    // Write Here
/*
*int cardslist =
*
*  */


                    PlayerList.get(PlayerTurn % PlayerList.size()).MoveTo(BoardSpaces[position]);

                    if(!(Dice[0].equals(Dice[1]))){
                        for(Player plr : PlayerList){
                            plr.PlayerIcon.setOpaque(false);
                        }
                        PlayerList.get((PlayerTurn + 1) % PlayerList.size()).PlayerIcon.setOpaque(true);
                        PlayerList.get((PlayerTurn + 1) % PlayerList.size()).PlayerIcon.revalidate();

                        RolledCard.PropertyLBL.setIcon(MonopolyUtils.PropertyIcons[position]);


                        PlayerTurn += 1;
                    }

                    timer.stop();
                }
                count++;
            }
        });
        timer.start();
    }
    static ArrayList<Player> RemoveNULL(ArrayList<Player> PlayerList){
        ArrayList<Player> res = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            if(PlayerList.get(i) != null){
                res.add(PlayerList.get(i));
            }
        }
        return res;
    }
}