/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AlphabetGame;
import controller.WordsGame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.DataGame;
import model.ScoreLine;
import observer.Observer;

/**
 *
 * @author actim
 */
public class MainFrame extends javax.swing.JFrame implements Observer {

    private DataGame dataGame;
    private AlphabetGame alphabetGame;
    private WordsGame wordsGame;
    private String font = "EMMETT";
    final int FONT_SIZE_TITLE = 10;
    final int FONT_SIZE_MENU = 40;
    final int FONT_SIZE_SCORES_BOARD = 50;
    private List<ScoreLine> scorelines;

    public MainFrame(DataGame dataGame) {

        this.dataGame = dataGame;
        this.dataGame.addObserver(this);

        initComponents();
        setIcon();

        Txt_Login.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (Txt_Login.getText().length() >= 16) // limit textfield to 3 characters
                {
                    e.consume();
                }
                if (e.getKeyChar() == ';') {
                    e.consume();
                }
            }
        });
        changePanel(Pnl_Login);

        this.setVisible(true);
    }

    private void changePanel(JPanel namePanel) {
        Pnl_Global.removeAll();
        Pnl_Global.repaint();
        Pnl_Global.revalidate();

        //Adding Pannels
        Pnl_Global.add(namePanel);
        Pnl_Global.repaint();
        Pnl_Global.revalidate();

        if (namePanel == Pnl_Login) {
            requestingFocusThread(Txt_Login);
            keyboardBind(Btn_Login, KeyEvent.VK_SPACE);
        }
        if (namePanel == Pnl_ChooseGame) {
            requestingFocusThread(Btn_LettersGame);
            keyboardBind(Btn_ReturnMain, KeyEvent.VK_ESCAPE);
        }
        if (namePanel == Pnl_MainMenu) {
            keyboardBind(Btn_Exit, KeyEvent.VK_ESCAPE);
            requestingFocusThread(Btn_Play);
            Lbl_HelloName.setText("Hello " + this.dataGame.getNickname() + "! What do you want to do?");
        }
        if (namePanel == Pnl_LettersMenu) {
            keyboardBind(Btn_Cancel_Letters, KeyEvent.VK_ESCAPE);
            requestingFocusThread(Btn_Alphabet);
        }
        if (namePanel == Pnl_WordsMenu) {
            keyboardBind(Btn_Cancel_Words, KeyEvent.VK_ESCAPE);    
            requestingFocusThread(Btn_ShortWords);
        }
        if (namePanel == Pnl_Game) {
            keyboardBind(Btn_ReturnMenu, KeyEvent.VK_ESCAPE);
            if (this.dataGame.getGameType() == 4) {
                Lbl_Correction.setText("Enter a word");
            } else { //Alphabet Word
                Lbl_Correction.setText("Press a letter");
                keyboardBind(Btn_PlayLetter, KeyEvent.VK_SPACE);
            }
        }
        if (namePanel == Pnl_Score) {
            keyboardBind(Btn_QuitScore, KeyEvent.VK_ESCAPE);
            requestingFocusThread(Btn_QuitScore);
        }
    }

    private void keyboardBind(JButton letterButton, int virtualKey) {
        InputMap im = letterButton.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = letterButton.getActionMap();
        im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
        am.put("clickMe", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JButton letterButton = (JButton) e.getSource();
                letterButton.doClick();
            }
        });
    }

    private void requestingFocusThread(JToggleButton button) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                button.requestFocus();
            }
        });
    }

    private void requestingFocusThread(JButton button) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                button.requestFocus();
            }
        });
    }

    private void requestingFocusThread(javax.swing.JTextField jtext) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jtext.requestFocus();
            }
        });
    }

    private void Btn_ChangeColorDefault(java.awt.event.FocusEvent evt) {
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }

    private void Btn_ChangeColorFocus(java.awt.event.FocusEvent evt) {
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }
    
    private void Btn_ChangeColorSelected(JToggleButton btn){
        UIManager.put("ToggleButton.select", new java.awt.Color(240, 255, 253));
        SwingUtilities.updateComponentTreeUI(btn);
    }
    
    private void Btn_ChangeColorUnselected(JToggleButton btn){
        UIManager.put("ToggleButton.select", new java.awt.Color(240, 240, 240));
        SwingUtilities.updateComponentTreeUI(btn);
    }
    
    private void loadGameFrame(boolean isTrainingMode) {
        this.dataGame.setTrainingMode(isTrainingMode);
        changePanel(Pnl_Game);
        if (this.dataGame.getGameType() != 4) {
            this.alphabetGame = new AlphabetGame(this.dataGame);
            this.alphabetGame.start();
        } else {
            this.wordsGame = new WordsGame(this.dataGame);
            this.wordsGame.start();
        }
        if (isTrainingMode) {
            Lbl_ScoreFixed.setText(" ");
            Lbl_ScoreVar.setText(" ");
            Lbl_TimerFixed.setText(" ");
            Lbl_TimerVar.setText(" ");
        } else {
            Lbl_ScoreFixed.setText("Score:");
            Lbl_TimerFixed.setText("Timer:");
            Lbl_ScoreFixed.setVisible(true);
            Lbl_ScoreVar.setVisible(true);
            Lbl_TimerFixed.setVisible(true);
            Lbl_TimerVar.setVisible(true);
        }
        changeKeyBoard(this.dataGame.getGameType());
    }

    private void changeKeyBoard(int mode) {
        Pnl_KeyBoard.removeAll();
        Pnl_KeyBoard.repaint();
        Pnl_KeyBoard.revalidate();
        switch (mode) {
            case 2:
                vowelsPanel.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(vowelsPanel);
                break;
            case 3:
                consonnantsPanel.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(consonnantsPanel);
                break;
            case 4:
                wordsPanel.setWordsGame(this.wordsGame);
                Pnl_KeyBoard.add(wordsPanel);
                wordsPanel.requestingFocusThreadTxt();
                break;
            default:
                alphabetPanel.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(alphabetPanel);
                break;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Pnl_Global = new javax.swing.JPanel();
        Pnl_Score = new javax.swing.JPanel();
        Lbl_FinalScoreVar = new javax.swing.JLabel();
        Lbl_FinalScoreFix = new javax.swing.JLabel();
        Btn_QuitScore = new javax.swing.JButton();
        Pnl_ListScores = new javax.swing.JPanel();
        Pnl_Game = new javax.swing.JPanel();
        Pnl_TopGame = new javax.swing.JPanel();
        Lbl_TimerFixed = new javax.swing.JLabel();
        Lbl_TimerVar = new javax.swing.JLabel();
        Lbl_ScoreFixed = new javax.swing.JLabel();
        Lbl_ScoreVar = new javax.swing.JLabel();
        Pnl_BotGame = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Btn_ReturnMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Pnl_CenGame = new javax.swing.JPanel();
        Pnl_KeyBoard = new javax.swing.JPanel();
        alphabetPanel = new view.Keyboard.AlphabetPanel();
        consonnantsPanel = new view.Keyboard.ConsonnantsPanel();
        vowelsPanel = new view.Keyboard.VowelsPanel();
        wordsPanel = new view.Keyboard.WordsPanel();
        Lbl_Correction = new javax.swing.JLabel();
        Btn_PlayLetter = new javax.swing.JButton();
        Pnl_LettersMenu = new javax.swing.JPanel();
        Lbl_Letters = new javax.swing.JLabel();
        Btn_Alphabet = new javax.swing.JToggleButton();
        Btn_Vowels = new javax.swing.JToggleButton();
        Btn_Consonnants = new javax.swing.JToggleButton();
        Lbl_Mode = new javax.swing.JLabel();
        Btn_Training = new javax.swing.JButton();
        Btn_Chrono = new javax.swing.JButton();
        Btn_Cancel_Letters = new javax.swing.JButton();
        Pnl_MainMenu = new javax.swing.JPanel();
        Btn_Play = new javax.swing.JButton();
        Btn_ChangeNickname = new javax.swing.JButton();
        Btn_Options = new javax.swing.JButton();
        Btn_Exit = new javax.swing.JButton();
        Lbl_Title = new javax.swing.JLabel();
        Lbl_Credits = new javax.swing.JLabel();
        Lbl_HelloName = new javax.swing.JLabel();
        Pnl_Login = new javax.swing.JPanel();
        Lbl_LoginFix = new javax.swing.JLabel();
        Txt_Login = new javax.swing.JTextField();
        Btn_Login = new javax.swing.JButton();
        Pnl_ChooseGame = new javax.swing.JPanel();
        Btn_LettersGame = new javax.swing.JButton();
        Btn_WordsGame = new javax.swing.JButton();
        Btn_ReturnMain = new javax.swing.JButton();
        Pnl_WordsMenu = new javax.swing.JPanel();
        Lbl_ModeW = new javax.swing.JLabel();
        Btn_TrainingW = new javax.swing.JButton();
        Btn_ChronoW = new javax.swing.JButton();
        Btn_Cancel_Words = new javax.swing.JButton();
        Lbl_WordsLength = new javax.swing.JLabel();
        Btn_ShortWords = new javax.swing.JToggleButton();
        Btn_MediumWords = new javax.swing.JToggleButton();
        Btn_EveryWords = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alpha Training");
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(600, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        Pnl_Global.setFocusable(false);
        Pnl_Global.setMinimumSize(new java.awt.Dimension(600, 400));
        Pnl_Global.setRequestFocusEnabled(false);
        Pnl_Global.setLayout(new java.awt.CardLayout());

        Pnl_Score.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_Score.setFocusable(false);
        Pnl_Score.setLayout(new java.awt.GridBagLayout());

        Lbl_FinalScoreVar.setText("x");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        Pnl_Score.add(Lbl_FinalScoreVar, gridBagConstraints);

        Lbl_FinalScoreFix.setText("Your score:");
        Pnl_Score.add(Lbl_FinalScoreFix, new java.awt.GridBagConstraints());

        Btn_QuitScore.setBackground(new java.awt.Color(240,240,240));
        Btn_QuitScore.setText("Return Menu");
        Btn_QuitScore.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_QuitScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_QuitScoreActionPerformed(evt);
            }
        });
        Btn_QuitScore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_QuitScoreKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        Pnl_Score.add(Btn_QuitScore, gridBagConstraints);

        Pnl_ListScores.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_ListScores.setLayout(new java.awt.GridLayout(11, 4, 20, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        Pnl_Score.add(Pnl_ListScores, gridBagConstraints);

        Pnl_Global.add(Pnl_Score, "card5");

        Pnl_Game.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_Game.setMinimumSize(new java.awt.Dimension(600, 500));
        Pnl_Game.setLayout(new java.awt.BorderLayout());

        Pnl_TopGame.setBackground(new java.awt.Color(255, 249, 242));
        java.awt.GridBagLayout Pnl_TopGameLayout = new java.awt.GridBagLayout();
        Pnl_TopGameLayout.columnWidths = new int[] {0, 400};
        Pnl_TopGame.setLayout(Pnl_TopGameLayout);

        Lbl_TimerFixed.setText("Timer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        Pnl_TopGame.add(Lbl_TimerFixed, gridBagConstraints);

        Lbl_TimerVar.setText("0s");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        Pnl_TopGame.add(Lbl_TimerVar, gridBagConstraints);

        Lbl_ScoreFixed.setText("Score:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        Pnl_TopGame.add(Lbl_ScoreFixed, gridBagConstraints);

        Lbl_ScoreVar.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        Pnl_TopGame.add(Lbl_ScoreVar, gridBagConstraints);

        Pnl_Game.add(Pnl_TopGame, java.awt.BorderLayout.PAGE_START);

        Pnl_BotGame.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_BotGame.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 249, 242));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        Pnl_BotGame.add(jPanel1, gridBagConstraints);

        Btn_ReturnMenu.setText("Return Menu");
        Btn_ReturnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ReturnMenuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        Pnl_BotGame.add(Btn_ReturnMenu, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 249, 242));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        Pnl_BotGame.add(jPanel2, gridBagConstraints);

        Pnl_Game.add(Pnl_BotGame, java.awt.BorderLayout.PAGE_END);

        Pnl_CenGame.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_CenGame.setLayout(new java.awt.GridBagLayout());

        Pnl_KeyBoard.setLayout(new java.awt.CardLayout());
        Pnl_KeyBoard.add(alphabetPanel, "card2");
        Pnl_KeyBoard.add(consonnantsPanel, "card3");

        vowelsPanel.setToolTipText("");
        Pnl_KeyBoard.add(vowelsPanel, "card4");
        Pnl_KeyBoard.add(wordsPanel, "card5");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_CenGame.add(Pnl_KeyBoard, gridBagConstraints);

        Lbl_Correction.setText("Press a letter...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 30, 10);
        Pnl_CenGame.add(Lbl_Correction, gridBagConstraints);

        Btn_PlayLetter.setText("Listen Letter (Press Space)");
        Btn_PlayLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PlayLetterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        Pnl_CenGame.add(Btn_PlayLetter, gridBagConstraints);

        Pnl_Game.add(Pnl_CenGame, java.awt.BorderLayout.CENTER);

        Pnl_Global.add(Pnl_Game, "card3");

        Pnl_LettersMenu.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_LettersMenu.setFocusable(false);
        Pnl_LettersMenu.setPreferredSize(new java.awt.Dimension(900, 600));
        Pnl_LettersMenu.setLayout(new java.awt.GridBagLayout());

        Lbl_Letters.setText("Choose your letters:");
        Lbl_Letters.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Lbl_Letters, gridBagConstraints);

        Btn_Alphabet.setBackground(new java.awt.Color(240,240,240));
        Btn_Alphabet.setText("Alphabet");
        Btn_Alphabet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Alphabet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_AlphabetMouseClicked(evt);
            }
        });
        Btn_Alphabet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AlphabetActionPerformed(evt);
            }
        });
        Btn_Alphabet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_AlphabetKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Btn_Alphabet, gridBagConstraints);

        Btn_Vowels.setBackground(new java.awt.Color(240,240,240));
        Btn_Vowels.setText("Vowels");
        Btn_Vowels.setRequestFocusEnabled(false);
        Btn_Vowels.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Vowels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_VowelsMouseClicked(evt);
            }
        });
        Btn_Vowels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_VowelsActionPerformed(evt);
            }
        });
        Btn_Vowels.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_VowelsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Btn_Vowels, gridBagConstraints);

        Btn_Consonnants.setBackground(new java.awt.Color(240,240,240));
        Btn_Consonnants.setText("Consonnants");
        Btn_Consonnants.setRequestFocusEnabled(false);
        Btn_Consonnants.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Consonnants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_ConsonnantsMouseClicked(evt);
            }
        });
        Btn_Consonnants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ConsonnantsActionPerformed(evt);
            }
        });
        Btn_Consonnants.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ConsonnantsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Btn_Consonnants, gridBagConstraints);

        Lbl_Mode.setText("Mode:");
        Lbl_Mode.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Lbl_Mode, gridBagConstraints);

        Btn_Training.setBackground(new java.awt.Color(240,240,240));
        Btn_Training.setText("Training");
        Btn_Training.setEnabled(false);
        Btn_Training.setRequestFocusEnabled(false);
        Btn_Training.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Training.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TrainingActionPerformed(evt);
            }
        });
        Btn_Training.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_TrainingKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Btn_Training, gridBagConstraints);

        Btn_Chrono.setBackground(new java.awt.Color(240,240,240));
        Btn_Chrono.setText("Chrono 30'");
        Btn_Chrono.setEnabled(false);
        Btn_Chrono.setRequestFocusEnabled(false);
        Btn_Chrono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Chrono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChronoActionPerformed(evt);
            }
        });
        Btn_Chrono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ChronoKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_LettersMenu.add(Btn_Chrono, gridBagConstraints);

        Btn_Cancel_Letters.setBackground(new java.awt.Color(240,240,240));
        Btn_Cancel_Letters.setText("Cancel");
        Btn_Cancel_Letters.setSelected(true);
        Btn_Cancel_Letters.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Cancel_Letters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cancel_LettersActionPerformed(evt);
            }
        });
        Btn_Cancel_Letters.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_Cancel_LettersKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        Pnl_LettersMenu.add(Btn_Cancel_Letters, gridBagConstraints);

        Pnl_Global.add(Pnl_LettersMenu, "card4");

        Pnl_MainMenu.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_MainMenu.setFocusable(false);
        Pnl_MainMenu.setPreferredSize(new java.awt.Dimension(900, 600));
        Pnl_MainMenu.setLayout(new java.awt.GridBagLayout());

        Btn_Play.setBackground(new java.awt.Color(240,240,240));
        Btn_Play.setFont(new Font(font, Font.PLAIN, 13));
        Btn_Play.setText("Play");
        Btn_Play.setSelected(true);
        Btn_Play.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PlayActionPerformed(evt);
            }
        });
        Btn_Play.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_PlayKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_Play, gridBagConstraints);

        Btn_ChangeNickname.setBackground(new java.awt.Color(240,240,240));
        Btn_ChangeNickname.setText("Change Nickname");
        Btn_ChangeNickname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_ChangeNicknameBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_ChangeNicknameBtn_PlayFocusLost(evt);
            }
        });
        Btn_ChangeNickname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChangeNicknameActionPerformed(evt);
            }
        });
        Btn_ChangeNickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ChangeNicknameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_ChangeNickname, gridBagConstraints);

        Btn_Options.setBackground(new java.awt.Color(240,240,240));
        Btn_Options.setText("Options");
        Btn_Options.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_OptionsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_Options, gridBagConstraints);

        Btn_Exit.setBackground(new java.awt.Color(240,240,240));
        Btn_Exit.setText("Exit");
        Btn_Exit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_PlayFocusLost(evt);
            }
        });
        Btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ExitActionPerformed(evt);
            }
        });
        Btn_Exit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ExitKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_Exit, gridBagConstraints);

        Lbl_Title.setFont(new java.awt.Font("Emmett", 0, 48)); // NOI18N
        Lbl_Title.setForeground(new java.awt.Color(0, 51, 102));
        Lbl_Title.setText("Alphabet Training");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        Pnl_MainMenu.add(Lbl_Title, gridBagConstraints);

        Lbl_Credits.setText("v0.7 - Credits: Mel-Srt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        Pnl_MainMenu.add(Lbl_Credits, gridBagConstraints);

        Lbl_HelloName.setText("\"Hello\"");
        Lbl_HelloName.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Lbl_HelloName, gridBagConstraints);

        Pnl_Global.add(Pnl_MainMenu, "card4");

        Pnl_Login.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_Login.setLayout(new java.awt.GridBagLayout());

        Lbl_LoginFix.setText("Enter your nickname:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        Pnl_Login.add(Lbl_LoginFix, gridBagConstraints);

        Txt_Login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Txt_LoginKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        Pnl_Login.add(Txt_Login, gridBagConstraints);

        Btn_Login.setBackground(new java.awt.Color(240,240,240));
        Btn_Login.setText("Confirm");
        Btn_Login.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_LoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_LoginFocusLost(evt);
            }
        });
        Btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LoginActionPerformed(evt);
            }
        });
        Btn_Login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_LoginKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        Pnl_Login.add(Btn_Login, gridBagConstraints);

        Pnl_Global.add(Pnl_Login, "card6");

        Pnl_ChooseGame.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_ChooseGame.setFocusable(false);
        Pnl_ChooseGame.setPreferredSize(new java.awt.Dimension(900, 600));
        Pnl_ChooseGame.setLayout(new java.awt.GridBagLayout());

        Btn_LettersGame.setBackground(new java.awt.Color(240,240,240));
        Btn_LettersGame.setFont(new Font(font, Font.PLAIN, 13));
        Btn_LettersGame.setText("Letters Game");
        Btn_LettersGame.setSelected(true);
        Btn_LettersGame.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_LettersGameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_LettersGameFocusLost(evt);
            }
        });
        Btn_LettersGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LettersGameActionPerformed(evt);
            }
        });
        Btn_LettersGame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_LettersGameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_ChooseGame.add(Btn_LettersGame, gridBagConstraints);

        Btn_WordsGame.setBackground(new java.awt.Color(240,240,240));
        Btn_WordsGame.setText("Words Game");
        Btn_WordsGame.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_WordsGameBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_WordsGameBtn_PlayFocusLost(evt);
            }
        });
        Btn_WordsGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_WordsGameActionPerformed(evt);
            }
        });
        Btn_WordsGame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_WordsGameKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_ChooseGame.add(Btn_WordsGame, gridBagConstraints);

        Btn_ReturnMain.setBackground(new java.awt.Color(240,240,240));
        Btn_ReturnMain.setText("Return");
        Btn_ReturnMain.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_ReturnMainBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_ReturnMainBtn_PlayFocusLost(evt);
            }
        });
        Btn_ReturnMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ReturnMainActionPerformed(evt);
            }
        });
        Btn_ReturnMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ReturnMainKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_ChooseGame.add(Btn_ReturnMain, gridBagConstraints);

        Pnl_Global.add(Pnl_ChooseGame, "card4");

        Pnl_WordsMenu.setBackground(new java.awt.Color(255, 249, 242));
        Pnl_WordsMenu.setFocusable(false);
        Pnl_WordsMenu.setPreferredSize(new java.awt.Dimension(900, 600));
        Pnl_WordsMenu.setLayout(new java.awt.GridBagLayout());

        Lbl_ModeW.setText("Mode:");
        Lbl_ModeW.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Lbl_ModeW, gridBagConstraints);

        Btn_TrainingW.setBackground(new java.awt.Color(240,240,240));
        Btn_TrainingW.setText("Training");
        Btn_TrainingW.setEnabled(false);
        Btn_TrainingW.setRequestFocusEnabled(false);
        Btn_TrainingW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_TrainingWBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_TrainingWBtn_PlayFocusLost(evt);
            }
        });
        Btn_TrainingW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TrainingWActionPerformed(evt);
            }
        });
        Btn_TrainingW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_TrainingWKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Btn_TrainingW, gridBagConstraints);

        Btn_ChronoW.setBackground(new java.awt.Color(240,240,240));
        Btn_ChronoW.setText("Chrono 60'");
        Btn_ChronoW.setEnabled(false);
        Btn_ChronoW.setRequestFocusEnabled(false);
        Btn_ChronoW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_ChronoWBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_ChronoWBtn_PlayFocusLost(evt);
            }
        });
        Btn_ChronoW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ChronoWActionPerformed(evt);
            }
        });
        Btn_ChronoW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ChronoWKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Btn_ChronoW, gridBagConstraints);

        Btn_Cancel_Words.setBackground(new java.awt.Color(240,240,240));
        Btn_Cancel_Words.setText("Cancel");
        Btn_Cancel_Words.setSelected(true);
        Btn_Cancel_Words.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_Cancel_WordsBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_Cancel_WordsBtn_PlayFocusLost(evt);
            }
        });
        Btn_Cancel_Words.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cancel_WordsActionPerformed(evt);
            }
        });
        Btn_Cancel_Words.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_Cancel_WordsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        Pnl_WordsMenu.add(Btn_Cancel_Words, gridBagConstraints);

        Lbl_WordsLength.setText("Words Length :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Lbl_WordsLength, gridBagConstraints);

        Btn_ShortWords.setBackground(new java.awt.Color(240,240,240));
        Btn_ShortWords.setText("Short");
        Btn_ShortWords.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_ShortWordsBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_ShortWordsBtn_PlayFocusLost(evt);
            }
        });
        Btn_ShortWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ShortWordsActionPerformed(evt);
            }
        });
        Btn_ShortWords.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_ShortWordsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Btn_ShortWords, gridBagConstraints);

        Btn_MediumWords.setBackground(new java.awt.Color(240,240,240));
        Btn_MediumWords.setText("Short and medium");
        Btn_MediumWords.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_MediumWordsBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_MediumWordsBtn_PlayFocusLost(evt);
            }
        });
        Btn_MediumWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_MediumWordsActionPerformed(evt);
            }
        });
        Btn_MediumWords.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_MediumWordsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Btn_MediumWords, gridBagConstraints);

        Btn_EveryWords.setBackground(new java.awt.Color(240,240,240));
        Btn_EveryWords.setText("All words");
        Btn_EveryWords.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Btn_EveryWordsBtn_PlayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Btn_EveryWordsBtn_PlayFocusLost(evt);
            }
        });
        Btn_EveryWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EveryWordsActionPerformed(evt);
            }
        });
        Btn_EveryWords.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Btn_EveryWordsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        Pnl_WordsMenu.add(Btn_EveryWords, gridBagConstraints);

        Pnl_Global.add(Pnl_WordsMenu, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pnl_Global, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pnl_Global, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_OptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_OptionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_OptionsActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int width = this.getWidth();
        int heigth = this.getHeight();

        //Login Menu
        Lbl_LoginFix.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Txt_Login.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Login.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));

        //Panel Main Menu
        Lbl_Title.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_TITLE));
        Lbl_HelloName.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Play.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_ChangeNickname.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Options.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Options.setVisible(false);
        Btn_Exit.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_Credits.setFont(new Font(font, Font.PLAIN, width / 45));
        //Space arount Title
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, heigth / 5, 0);
        Pnl_MainMenu.add(Lbl_Title, gridBagConstraints);

        //Panel Choose Game (Letters or Words)
        Btn_LettersGame.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_WordsGame.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_ReturnMain.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));

        //Panel Letters Menu
        Lbl_Letters.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Alphabet.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Vowels.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Consonnants.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_Mode.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Training.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Chrono.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Cancel_Letters.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));

        //Panel Words Menu
        Lbl_WordsLength.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_ShortWords.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_MediumWords.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_EveryWords.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_ModeW.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_TrainingW.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_ChronoW.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_Cancel_Words.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));

        //Panel Game
        Btn_ReturnMenu.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Btn_PlayLetter.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_ScoreFixed.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_ScoreVar.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_TimerFixed.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_TimerVar.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_Correction.setFont(new Font(font, Font.PLAIN, width / 25));
        if (this.dataGame.getGameType() == 4) { //Words Game
            //Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 200, (heigth / 2)));
            Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 200, (heigth / 3) + 50));
        } else {
            //Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 100, (heigth / 3)));
            Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 200, (heigth / 4) + 50));
        }

        //Panel Score
        Btn_QuitScore.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_MENU));
        Lbl_FinalScoreFix.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_FinalScoreVar.setFont(new Font(font, Font.PLAIN, width / 30));

        for (Component cp : Pnl_ListScores.getComponents()) {
            cp.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_SCORES_BOARD));
        }

    }//GEN-LAST:event_formComponentResized

    private void Btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ExitActionPerformed
        dispose();
    }//GEN-LAST:event_Btn_ExitActionPerformed

    private void Btn_PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PlayActionPerformed
        changePanel(Pnl_ChooseGame);
    }//GEN-LAST:event_Btn_PlayActionPerformed

    private void Btn_Cancel_LettersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cancel_LettersActionPerformed
        changePanel(Pnl_ChooseGame);
    }//GEN-LAST:event_Btn_Cancel_LettersActionPerformed

    private void Btn_AlphabetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AlphabetActionPerformed

        if (Btn_Alphabet.isSelected()) {
            this.dataGame.setGameType("Standard A-Z");
            Btn_Vowels.setSelected(false);
            Btn_Consonnants.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
            Btn_ChangeColorSelected(Btn_Alphabet);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_AlphabetActionPerformed

    private void Btn_VowelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_VowelsActionPerformed

        if (Btn_Vowels.isSelected()) {
            this.dataGame.setGameType("Vowels");
            Btn_Alphabet.setSelected(false);
            Btn_Consonnants.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
            Btn_ChangeColorSelected(Btn_Vowels);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_VowelsActionPerformed

    private void Btn_ConsonnantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ConsonnantsActionPerformed
        // TODO add your handling code here:
        if (Btn_Consonnants.isSelected()) {
            this.dataGame.setGameType("Consonnants");
            Btn_Alphabet.setSelected(false);
            Btn_Vowels.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
            Btn_ChangeColorSelected(Btn_Consonnants);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_ConsonnantsActionPerformed

    private void Btn_TrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TrainingActionPerformed
        loadGameFrame(true);
    }//GEN-LAST:event_Btn_TrainingActionPerformed

    private void Btn_ChronoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChronoActionPerformed
        loadGameFrame(false);
    }//GEN-LAST:event_Btn_ChronoActionPerformed

    private void Btn_ReturnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ReturnMenuActionPerformed
        if (this.dataGame.getGameType() == 4) {
            this.wordsGame.stop(true);
            changePanel(Pnl_WordsMenu);
        } else {
            this.alphabetGame.stop(true);
            changePanel(Pnl_LettersMenu);
        }
    }//GEN-LAST:event_Btn_ReturnMenuActionPerformed

    private void Btn_PlayLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PlayLetterActionPerformed
        if(this.dataGame.getGameType() == 4){
            this.wordsGame.playWord();
        }
        else alphabetGame.playLetter();
    }//GEN-LAST:event_Btn_PlayLetterActionPerformed

    private void Btn_QuitScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_QuitScoreActionPerformed
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_QuitScoreActionPerformed

    //Navigation by Keyboard

    private void Btn_PlayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_PlayKeyPressed
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_ChangeNickname.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Play.doClick();
        }
    }//GEN-LAST:event_Btn_PlayKeyPressed

    private void Btn_ExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ExitKeyPressed
        // Top Arrow
        if (evt.getKeyCode() == 38) {
            Btn_ChangeNickname.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Exit.doClick();
        }
    }//GEN-LAST:event_Btn_ExitKeyPressed

    private void Btn_AlphabetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_AlphabetKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Alphabet.doClick();
        }//Down Arrow:
        if (evt.getKeyCode() == 40) {
            if (Btn_Training.isEnabled()) {
                Btn_Training.requestFocus();
            } else {
                Btn_Cancel_Letters.requestFocus();
            }
        }
        //Right Arrow
        if (evt.getKeyCode() == 39) {
            Btn_Vowels.requestFocus();
        }
    }//GEN-LAST:event_Btn_AlphabetKeyPressed

    private void Btn_VowelsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_VowelsKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Vowels.doClick();
        }//Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_Alphabet.requestFocus();
        }
        //Right Arrow
        if (evt.getKeyCode() == 39) {
            Btn_Consonnants.requestFocus();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Cancel_Letters.requestFocus();
        }
    }//GEN-LAST:event_Btn_VowelsKeyPressed

    private void Btn_ConsonnantsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ConsonnantsKeyPressed
        ///Enter
        if (evt.getKeyCode() == 10) {
            Btn_Consonnants.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            if (Btn_Chrono.isEnabled()) {
                Btn_Chrono.requestFocus();
            } else {
                Btn_Cancel_Letters.requestFocus();
            }
        }
        //Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_Vowels.requestFocus();
        }
    }//GEN-LAST:event_Btn_ConsonnantsKeyPressed

    private void Btn_Cancel_LettersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_Cancel_LettersKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Cancel_Letters.requestFocus();
        }
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_Vowels.requestFocus();
        }
        //Left Arrow:
        if (evt.getKeyCode() == 37) {
            if (Btn_Training.isEnabled()) {
                Btn_Training.requestFocus();
            }
        }
        //Right Arrow:
        if (evt.getKeyCode() == 39) {
            if (Btn_Chrono.isEnabled()) {
                Btn_Chrono.requestFocus();
            }
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Cancel_Letters.doClick();
        }
    }//GEN-LAST:event_Btn_Cancel_LettersKeyPressed

    private void Btn_TrainingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_TrainingKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Training.doClick();
        }
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_Alphabet.requestFocus();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Cancel_Letters.requestFocus();
        }
        //Right Arrow
        if (evt.getKeyCode() == 39) {
            Btn_Chrono.requestFocus();
        }
    }//GEN-LAST:event_Btn_TrainingKeyPressed

    private void Btn_ChronoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ChronoKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Chrono.doClick();
        }
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_Consonnants.requestFocus();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Cancel_Letters.requestFocus();
        }
        //Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_Training.requestFocus();
        }
    }//GEN-LAST:event_Btn_ChronoKeyPressed

    private void Btn_QuitScoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_QuitScoreKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_QuitScore.doClick();
        }
    }//GEN-LAST:event_Btn_QuitScoreKeyPressed

    private void Btn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_PlayFocusGained
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
        if (evt.getComponent() == Btn_Alphabet && Btn_Alphabet.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(153, 204, 255));
            SwingUtilities.updateComponentTreeUI(Btn_Alphabet);
        } else if (evt.getComponent() == Btn_Vowels && Btn_Vowels.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(153, 204, 255));
            SwingUtilities.updateComponentTreeUI(Btn_Vowels);
        } else if (evt.getComponent() == Btn_Consonnants && Btn_Consonnants.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(153, 204, 255));
            SwingUtilities.updateComponentTreeUI(Btn_Consonnants);
        }
    }//GEN-LAST:event_Btn_PlayFocusGained

    private void Btn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_PlayFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
        if (evt.getComponent() == Btn_Alphabet && Btn_Alphabet.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(240, 255, 253));
            SwingUtilities.updateComponentTreeUI(Btn_Alphabet);
        } else if (evt.getComponent() == Btn_Vowels && Btn_Vowels.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(240, 255, 253));
            SwingUtilities.updateComponentTreeUI(Btn_Vowels);
        } else if (evt.getComponent() == Btn_Consonnants && Btn_Consonnants.isSelected()) {
            UIManager.put("ToggleButton.select", new java.awt.Color(240, 255, 253));
            SwingUtilities.updateComponentTreeUI(Btn_Consonnants);
        }
    }//GEN-LAST:event_Btn_PlayFocusLost

    private void Btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LoginActionPerformed
        this.dataGame.setNickname(Txt_Login.getText());
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_LoginActionPerformed

    private void Txt_LoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_LoginKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Login.doClick();
        }
        //Down arrow (40) 
        if (evt.getKeyCode() == 40) {
            Btn_Login.requestFocus();
        }
    }//GEN-LAST:event_Txt_LoginKeyPressed

    private void Btn_LoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_LoginKeyPressed
        // TODO add your handling code here:
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Login.doClick();
        }
        //Up arrow (40) 
        if (evt.getKeyCode() == 38) {
            Txt_Login.requestFocus();
        }
    }//GEN-LAST:event_Btn_LoginKeyPressed

    private void Btn_ChangeNicknameBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ChangeNicknameBtn_PlayFocusGained
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }//GEN-LAST:event_Btn_ChangeNicknameBtn_PlayFocusGained

    private void Btn_ChangeNicknameBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ChangeNicknameBtn_PlayFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_Btn_ChangeNicknameBtn_PlayFocusLost

    private void Btn_ChangeNicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChangeNicknameActionPerformed
        // TODO add your handling code here:
        changePanel(Pnl_Login);
    }//GEN-LAST:event_Btn_ChangeNicknameActionPerformed

    private void Btn_ChangeNicknameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ChangeNicknameKeyPressed
        // TODO add your handling code here:
        // Top Arrow
        if (evt.getKeyCode() == 38) {
            Btn_Play.requestFocus();
        }
        //Down arrow
        if (evt.getKeyCode() == 40) {
            Btn_Exit.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_ChangeNickname.doClick();
        }
    }//GEN-LAST:event_Btn_ChangeNicknameKeyPressed

    private void Btn_VowelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_VowelsMouseClicked
        // TODO add your handling code here:
        Btn_Vowels.requestFocus();
        Btn_Vowels.doClick();
    }//GEN-LAST:event_Btn_VowelsMouseClicked

    private void Btn_ConsonnantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_ConsonnantsMouseClicked
        // TODO add your handling code here:
        Btn_Consonnants.requestFocus();
        Btn_Consonnants.doClick();
    }//GEN-LAST:event_Btn_ConsonnantsMouseClicked

    private void Btn_AlphabetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_AlphabetMouseClicked
        // TODO add your handling code here:
        Btn_Alphabet.requestFocus();
        Btn_Alphabet.doClick();
    }//GEN-LAST:event_Btn_AlphabetMouseClicked

    private void Btn_LettersGameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_LettersGameFocusGained
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }//GEN-LAST:event_Btn_LettersGameFocusGained

    private void Btn_LettersGameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_LettersGameFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_Btn_LettersGameFocusLost

    private void Btn_LettersGameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_LettersGameKeyPressed
        // TODO add your handling code here:
        //Down arrow
        if (evt.getKeyCode() == 40) {
            Btn_WordsGame.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_LettersGame.doClick();
        }
    }//GEN-LAST:event_Btn_LettersGameKeyPressed

    private void Btn_WordsGameBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_WordsGameBtn_PlayFocusGained
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }//GEN-LAST:event_Btn_WordsGameBtn_PlayFocusGained

    private void Btn_WordsGameBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_WordsGameBtn_PlayFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_Btn_WordsGameBtn_PlayFocusLost

    private void Btn_WordsGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_WordsGameActionPerformed
        // TODO add your handling code here:
        this.dataGame.setGameType("Words");
        changePanel(Pnl_WordsMenu);
    }//GEN-LAST:event_Btn_WordsGameActionPerformed

    private void Btn_WordsGameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_WordsGameKeyPressed

        // Top Arrow
        if (evt.getKeyCode() == 38) {
            Btn_LettersGame.requestFocus();
        }
        //Down arrow
        if (evt.getKeyCode() == 40) {
            Btn_ReturnMain.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_WordsGame.doClick();
        }
    }//GEN-LAST:event_Btn_WordsGameKeyPressed

    private void Btn_ReturnMainBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ReturnMainBtn_PlayFocusGained
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }//GEN-LAST:event_Btn_ReturnMainBtn_PlayFocusGained

    private void Btn_ReturnMainBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ReturnMainBtn_PlayFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_Btn_ReturnMainBtn_PlayFocusLost

    private void Btn_ReturnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ReturnMainActionPerformed
        // TODO add your handling code here:
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_ReturnMainActionPerformed

    private void Btn_ReturnMainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ReturnMainKeyPressed
        // TODO add your handling code here:
        // Top Arrow
        if (evt.getKeyCode() == 38) {
            Btn_WordsGame.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_ReturnMain.doClick();
        }
    }//GEN-LAST:event_Btn_ReturnMainKeyPressed

    private void Btn_LettersGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LettersGameActionPerformed
        // TODO add your handling code here:
        changePanel(Pnl_LettersMenu);
    }//GEN-LAST:event_Btn_LettersGameActionPerformed

    private void Btn_LoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_LoginFocusGained
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(153, 204, 255));
    }//GEN-LAST:event_Btn_LoginFocusGained

    private void Btn_LoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_LoginFocusLost
        // TODO add your handling code here:
        evt.getComponent().setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_Btn_LoginFocusLost

    private void Btn_TrainingWBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_TrainingWBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_TrainingWBtn_PlayFocusGained

    private void Btn_TrainingWBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_TrainingWBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_TrainingWBtn_PlayFocusLost

    private void Btn_TrainingWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TrainingWActionPerformed
        loadGameFrame(true);
    }//GEN-LAST:event_Btn_TrainingWActionPerformed

    private void Btn_TrainingWKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_TrainingWKeyPressed
        // TODO add your handling code here:
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_ShortWords.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_TrainingW.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Cancel_Words.requestFocus();
        }
        //Right Arrow
        if (evt.getKeyCode() == 39) {
            Btn_ChronoW.requestFocus();
        }
    }//GEN-LAST:event_Btn_TrainingWKeyPressed

    private void Btn_ChronoWBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ChronoWBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_ChronoWBtn_PlayFocusGained

    private void Btn_ChronoWBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ChronoWBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_ChronoWBtn_PlayFocusLost

    private void Btn_ChronoWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChronoWActionPerformed
        // TODO add your handling code here:
        loadGameFrame(false);
    }//GEN-LAST:event_Btn_ChronoWActionPerformed

    private void Btn_ChronoWKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ChronoWKeyPressed
        // TODO add your handling code here:
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_EveryWords.requestFocus();
        }
        
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_ChronoW.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Cancel_Words.requestFocus();
        }
        //Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_TrainingW.requestFocus();
        }
    }//GEN-LAST:event_Btn_ChronoWKeyPressed

    private void Btn_Cancel_WordsBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_Cancel_WordsBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_Cancel_WordsBtn_PlayFocusGained

    private void Btn_Cancel_WordsBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_Cancel_WordsBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_Cancel_WordsBtn_PlayFocusLost

    private void Btn_Cancel_WordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cancel_WordsActionPerformed
        // TODO add your handling code here:
        changePanel(Pnl_ChooseGame);
    }//GEN-LAST:event_Btn_Cancel_WordsActionPerformed

    private void Btn_Cancel_WordsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_Cancel_WordsKeyPressed
        // TODO add your handling code here:
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Cancel_Words.doClick();
        }
        //Up Arrow:
        if (evt.getKeyCode() == 38) {
            Btn_TrainingW.requestFocus();
        }
    }//GEN-LAST:event_Btn_Cancel_WordsKeyPressed

    private void Btn_ShortWordsBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ShortWordsBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_ShortWordsBtn_PlayFocusGained

    private void Btn_ShortWordsBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_ShortWordsBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_ShortWordsBtn_PlayFocusLost

    private void Btn_ShortWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ShortWordsActionPerformed
        // TODO add your handling code here:
        if (Btn_ShortWords.isSelected()) {
            this.dataGame.setWordLengthMax(5);
            Btn_MediumWords.setSelected(false);
            Btn_EveryWords.setSelected(false);
            Btn_TrainingW.setEnabled(true);
            Btn_ChronoW.setEnabled(true);
            Btn_ChangeColorSelected(Btn_ShortWords);
        } else {
            Btn_TrainingW.setEnabled(false);
            Btn_ChronoW.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_ShortWordsActionPerformed

    private void Btn_ShortWordsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ShortWordsKeyPressed
        // TODO add your handling code here:
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_ShortWords.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_TrainingW.requestFocus();
        }
        //Left Arrow
        if (evt.getKeyCode() == 39) {
            Btn_MediumWords.requestFocus();
        }
    }//GEN-LAST:event_Btn_ShortWordsKeyPressed

    private void Btn_MediumWordsBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_MediumWordsBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_MediumWordsBtn_PlayFocusGained

    private void Btn_MediumWordsBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_MediumWordsBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_MediumWordsBtn_PlayFocusLost

    private void Btn_MediumWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_MediumWordsActionPerformed
       // TODO add your handling code here:
       if (Btn_MediumWords.isSelected()) {
            this.dataGame.setWordLengthMax(8);
            Btn_ShortWords.setSelected(false);
            Btn_EveryWords.setSelected(false);
            Btn_TrainingW.setEnabled(true);
            Btn_ChronoW.setEnabled(true);
            Btn_ChangeColorSelected(Btn_MediumWords);
        } else {
            Btn_TrainingW.setEnabled(false);
            Btn_ChronoW.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_MediumWordsActionPerformed

    private void Btn_MediumWordsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_MediumWordsKeyPressed
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_MediumWords.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_TrainingW.requestFocus();
        }
        //Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_ShortWords.requestFocus();
        }
        //Right Arrow
        if (evt.getKeyCode() == 39) {
            Btn_EveryWords.requestFocus();
        }
    }//GEN-LAST:event_Btn_MediumWordsKeyPressed

    private void Btn_EveryWordsBtn_PlayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_EveryWordsBtn_PlayFocusGained
        // TODO add your handling code here:
        Btn_ChangeColorFocus(evt);
    }//GEN-LAST:event_Btn_EveryWordsBtn_PlayFocusGained

    private void Btn_EveryWordsBtn_PlayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Btn_EveryWordsBtn_PlayFocusLost
        // TODO add your handling code here:
        Btn_ChangeColorDefault(evt);
    }//GEN-LAST:event_Btn_EveryWordsBtn_PlayFocusLost

    private void Btn_EveryWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EveryWordsActionPerformed
        // TODO add your handling code here:
        if (Btn_EveryWords.isSelected()) {
            this.dataGame.setWordLengthMax(5);
            Btn_MediumWords.setSelected(false);
            Btn_ShortWords.setSelected(false);
            Btn_TrainingW.setEnabled(true);
            Btn_ChronoW.setEnabled(true);
            Btn_ChangeColorSelected(Btn_EveryWords);
        } else {
            Btn_TrainingW.setEnabled(false);
            Btn_ChronoW.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_EveryWordsActionPerformed

    private void Btn_EveryWordsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_EveryWordsKeyPressed
        // TODO add your handling code here:
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_EveryWords.doClick();
        }
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_ChronoW.requestFocus();
        }
        //Left Arrow
        if (evt.getKeyCode() == 37) {
            Btn_MediumWords.requestFocus();
        }
    }//GEN-LAST:event_Btn_EveryWordsKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Btn_Alphabet;
    private javax.swing.JButton Btn_Cancel_Letters;
    private javax.swing.JButton Btn_Cancel_Words;
    private javax.swing.JButton Btn_ChangeNickname;
    private javax.swing.JButton Btn_Chrono;
    private javax.swing.JButton Btn_ChronoW;
    private javax.swing.JToggleButton Btn_Consonnants;
    private javax.swing.JToggleButton Btn_EveryWords;
    private javax.swing.JButton Btn_Exit;
    private javax.swing.JButton Btn_LettersGame;
    private javax.swing.JButton Btn_Login;
    private javax.swing.JToggleButton Btn_MediumWords;
    private javax.swing.JButton Btn_Options;
    private javax.swing.JButton Btn_Play;
    private javax.swing.JButton Btn_PlayLetter;
    private javax.swing.JButton Btn_QuitScore;
    private javax.swing.JButton Btn_ReturnMain;
    private javax.swing.JButton Btn_ReturnMenu;
    private javax.swing.JToggleButton Btn_ShortWords;
    private javax.swing.JButton Btn_Training;
    private javax.swing.JButton Btn_TrainingW;
    private javax.swing.JToggleButton Btn_Vowels;
    private javax.swing.JButton Btn_WordsGame;
    private javax.swing.JLabel Lbl_Correction;
    private javax.swing.JLabel Lbl_Credits;
    private javax.swing.JLabel Lbl_FinalScoreFix;
    private javax.swing.JLabel Lbl_FinalScoreVar;
    private javax.swing.JLabel Lbl_HelloName;
    private javax.swing.JLabel Lbl_Letters;
    private javax.swing.JLabel Lbl_LoginFix;
    private javax.swing.JLabel Lbl_Mode;
    private javax.swing.JLabel Lbl_ModeW;
    private javax.swing.JLabel Lbl_ScoreFixed;
    private javax.swing.JLabel Lbl_ScoreVar;
    private javax.swing.JLabel Lbl_TimerFixed;
    private javax.swing.JLabel Lbl_TimerVar;
    private javax.swing.JLabel Lbl_Title;
    private javax.swing.JLabel Lbl_WordsLength;
    private javax.swing.JPanel Pnl_BotGame;
    private javax.swing.JPanel Pnl_CenGame;
    private javax.swing.JPanel Pnl_ChooseGame;
    private javax.swing.JPanel Pnl_Game;
    private javax.swing.JPanel Pnl_Global;
    private javax.swing.JPanel Pnl_KeyBoard;
    private javax.swing.JPanel Pnl_LettersMenu;
    private javax.swing.JPanel Pnl_ListScores;
    private javax.swing.JPanel Pnl_Login;
    private javax.swing.JPanel Pnl_MainMenu;
    private javax.swing.JPanel Pnl_Score;
    private javax.swing.JPanel Pnl_TopGame;
    private javax.swing.JPanel Pnl_WordsMenu;
    private javax.swing.JTextField Txt_Login;
    private view.Keyboard.AlphabetPanel alphabetPanel;
    private view.Keyboard.ConsonnantsPanel consonnantsPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private view.Keyboard.VowelsPanel vowelsPanel;
    private view.Keyboard.WordsPanel wordsPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(String string) {
    }

    @Override
    public void updateTimer(String string) {
        Lbl_TimerVar.setText(string);
    }

    @Override
    public void updateScore(String string) {
        Lbl_ScoreVar.setText(string);
    }

    /**
     *
     * @param currentScore
     * @param scoreLines
     */
    @Override
    public void updateEndGame(ScoreLine currentScore, List<ScoreLine> scoreLines) {

        Lbl_Correction.setText("Press a letter...");
        Pnl_ListScores.removeAll();
        if (currentScore != null) {
            Lbl_FinalScoreVar.setText(currentScore.getScoreFormat());
            Lbl_FinalScoreVar.setForeground(Color.red);
            this.scorelines = scoreLines;

            System.out.println("count : " + Pnl_ListScores.getComponentCount());
            Pnl_ListScores.removeAll();

            //Create name of columns of the grid
            JLabel Lbl_position = new javax.swing.JLabel("", SwingConstants.CENTER);
            Lbl_position.setText("Position");
            Pnl_ListScores.add(Lbl_position);
            JLabel Lbl_sPseudo = new javax.swing.JLabel("", SwingConstants.CENTER);
            Lbl_sPseudo.setText("Pseudo");
            Pnl_ListScores.add(Lbl_sPseudo);
            JLabel Lbl_sDate = new javax.swing.JLabel("", SwingConstants.CENTER);
            Lbl_sDate.setText("Date");
            Pnl_ListScores.add(Lbl_sDate);
            JLabel Lbl_sScore = new javax.swing.JLabel("", SwingConstants.CENTER);
            Lbl_sScore.setText("Score");
            Pnl_ListScores.add(Lbl_sScore);

            //Fill the grid
            int nbScoreLines = scoreLines.size();
            for (int i = 0; i < 10; i++) {
                JLabel Lbl_pos = new javax.swing.JLabel("", SwingConstants.CENTER);
                JLabel Lbl_sn = new javax.swing.JLabel("", SwingConstants.CENTER);
                JLabel Lbl_sd = new javax.swing.JLabel("", SwingConstants.CENTER);
                JLabel Lbl_ss = new javax.swing.JLabel("", SwingConstants.CENTER);

                if (i < nbScoreLines) {
                    Lbl_pos.setText(Integer.toString(i + 1));
                    Lbl_sn.setText(scoreLines.get(i).getPseudo());
                    Lbl_sd.setText(scoreLines.get(i).getDate());
                    Lbl_ss.setText(scoreLines.get(i).getScoreFormat());

                    if (Objects.equals(scoreLines.get(i).getDate(), currentScore.getDate())) {
                        Lbl_pos.setForeground(Color.red);
                        Lbl_sn.setForeground(Color.red);
                        Lbl_sd.setForeground(Color.red);
                        Lbl_ss.setForeground(Color.red);
                    }
                } else {
                    Lbl_pos.setText(Integer.toString(i + 1));
                    Lbl_sn.setText(" ");
                    Lbl_sd.setText(" ");
                    Lbl_ss.setText(" ");
                }
                Pnl_ListScores.add(Lbl_pos);
                Pnl_ListScores.add(Lbl_sn);
                Pnl_ListScores.add(Lbl_sd);
                Pnl_ListScores.add(Lbl_ss);
            }
        }
        int width = this.getWidth();
        for (Component cp : Pnl_ListScores.getComponents()) {
            cp.setFont(new Font(font, Font.PLAIN, width / FONT_SIZE_SCORES_BOARD));
        }
        Pnl_ListScores.revalidate();
        Pnl_ListScores.repaint();
        changePanel(Pnl_Score);

    }

    public void updateCorrection(boolean rightOrFalse, char letter) {
        if (rightOrFalse) {
            this.Lbl_Correction.setText(letter + "  Correct!");
        } else {
            this.Lbl_Correction.setText(letter + "  False!");
        }
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("english.png")));
    }

    @Override
    public void updateCorrectionWord(boolean rightOrFalse, String word) {
        if (rightOrFalse) {
            this.Lbl_Correction.setText(word + " - Correct!");
        } else {
            this.Lbl_Correction.setText(word + " - False!");
        }
    }
}
