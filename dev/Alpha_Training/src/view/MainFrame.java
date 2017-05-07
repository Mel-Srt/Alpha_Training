/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AlphabetGame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import observer.Observer;

/**
 *
 * @author actim
 */
public class MainFrame extends javax.swing.JFrame implements Observer {

    private AlphabetGame alphabetGame;
    private String font = "EMMETT";

    public MainFrame(AlphabetGame alphabetGame) {

        this.alphabetGame = alphabetGame;
        initComponents();
        changePanel(Pnl_MainMenu);
        keyboardBind(Btn_Exit, KeyEvent.VK_ESCAPE);
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

        if (namePanel == Pnl_MainMenu) {
            requestingFocusThread(Btn_Play);
        }
        if (namePanel == Pnl_LettersMenu) {
            keyboardBind(Btn_Cancel_Letters, KeyEvent.VK_ESCAPE);
            requestingFocusThread(Btn_Alphabet);
        }
        if (namePanel == Pnl_Game) {
            keyboardBind(Btn_ReturnMenu, KeyEvent.VK_ESCAPE);
            keyboardBind(Btn_PlayLetter, KeyEvent.VK_SPACE);
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
        alphabetPanel1 = new view.Panels.AlphabetPanel();
        consonnantsPanel1 = new view.Panels.ConsonnantsPanel();
        vowelsPanel1 = new view.Panels.VowelsPanel();
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
        Btn_Options = new javax.swing.JButton();
        Btn_Exit = new javax.swing.JButton();
        Lbl_Title = new javax.swing.JLabel();
        Lbl_Credits = new javax.swing.JLabel();

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

        Pnl_Score.setBackground(new java.awt.Color(255, 242, 225));
        Pnl_Score.setFocusable(false);
        java.awt.GridBagLayout Pnl_ScoreLayout = new java.awt.GridBagLayout();
        Pnl_ScoreLayout.rowHeights = new int[] {100, 100, 100, 100, 100};
        Pnl_Score.setLayout(Pnl_ScoreLayout);

        Lbl_FinalScoreVar.setText("x");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        Pnl_Score.add(Lbl_FinalScoreVar, gridBagConstraints);

        Lbl_FinalScoreFix.setText("Your score:");
        Pnl_Score.add(Lbl_FinalScoreFix, new java.awt.GridBagConstraints());

        Btn_QuitScore.setText("Return Menu");
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
        Pnl_Score.add(Btn_QuitScore, gridBagConstraints);

        Pnl_Global.add(Pnl_Score, "card5");

        Pnl_Game.setMinimumSize(new java.awt.Dimension(600, 500));
        Pnl_Game.setLayout(new java.awt.BorderLayout());

        Pnl_TopGame.setBackground(new java.awt.Color(255, 242, 225));
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

        Pnl_BotGame.setBackground(new java.awt.Color(255, 242, 225));
        Pnl_BotGame.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 242, 225));

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

        jPanel2.setBackground(new java.awt.Color(255, 242, 225));

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

        Pnl_CenGame.setBackground(new java.awt.Color(255, 242, 225));
        Pnl_CenGame.setLayout(new java.awt.GridBagLayout());

        Pnl_KeyBoard.setLayout(new java.awt.CardLayout());
        Pnl_KeyBoard.add(alphabetPanel1, "card2");
        Pnl_KeyBoard.add(consonnantsPanel1, "card3");

        vowelsPanel1.setToolTipText("");
        Pnl_KeyBoard.add(vowelsPanel1, "card4");

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

        Pnl_LettersMenu.setBackground(new java.awt.Color(255, 242, 225));
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

        Btn_Alphabet.setText("Alphabet");
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

        Btn_Vowels.setText("Vowels");
        Btn_Vowels.setRequestFocusEnabled(false);
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

        Btn_Consonnants.setText("Consonnants");
        Btn_Consonnants.setRequestFocusEnabled(false);
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

        Btn_Training.setText("Training");
        Btn_Training.setEnabled(false);
        Btn_Training.setRequestFocusEnabled(false);
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

        Btn_Chrono.setText("Chrono 30'");
        Btn_Chrono.setEnabled(false);
        Btn_Chrono.setRequestFocusEnabled(false);
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

        Btn_Cancel_Letters.setText("Cancel");
        Btn_Cancel_Letters.setSelected(true);
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

        Pnl_MainMenu.setBackground(new java.awt.Color(255, 242, 225));
        Pnl_MainMenu.setFocusable(false);
        Pnl_MainMenu.setPreferredSize(new java.awt.Dimension(900, 600));
        Pnl_MainMenu.setLayout(new java.awt.GridBagLayout());

        Btn_Play.setFont(new Font(font, Font.PLAIN, 13));
        Btn_Play.setText("Play");
        Btn_Play.setSelected(true);
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

        Btn_Options.setText("Options");
        Btn_Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_OptionsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_Options, gridBagConstraints);

        Btn_Exit.setText("Exit");
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        Pnl_MainMenu.add(Btn_Exit, gridBagConstraints);

        Lbl_Title.setFont(new java.awt.Font("Emmett", 0, 48)); // NOI18N
        Lbl_Title.setForeground(new java.awt.Color(0, 51, 102));
        Lbl_Title.setText("Alpha Training v0.5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        Pnl_MainMenu.add(Lbl_Title, gridBagConstraints);

        Lbl_Credits.setText("Credits: Mel-Srt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        Pnl_MainMenu.add(Lbl_Credits, gridBagConstraints);

        Pnl_Global.add(Pnl_MainMenu, "card4");

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
        //Panel Main Menu
        Lbl_Title.setFont(new Font(font, Font.PLAIN, width / 10));
        Btn_Play.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Options.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Options.setVisible(false);
        Btn_Exit.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_Credits.setFont(new Font(font, Font.PLAIN, width / 45));
        //Space arount Title
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, heigth / 5, 0);
        Pnl_MainMenu.add(Lbl_Title, gridBagConstraints);

        //Panel Letters Menu
        Lbl_Letters.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Alphabet.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Vowels.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Consonnants.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_Mode.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Training.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Chrono.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_Cancel_Letters.setFont(new Font(font, Font.PLAIN, width / 40));

        //Panel Game
        Btn_ReturnMenu.setFont(new Font(font, Font.PLAIN, width / 40));
        Btn_PlayLetter.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_ScoreFixed.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_ScoreVar.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_TimerFixed.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_TimerVar.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_Correction.setFont(new Font(font, Font.PLAIN, width / 25));
        Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 200, (heigth / 2)));
        Pnl_KeyBoard.setPreferredSize(new Dimension((width / 2) + 200, (heigth / 3)+50));
        
        //Panel SCore
        Btn_QuitScore.setFont(new Font(font, Font.PLAIN, width / 40));
        Lbl_FinalScoreFix.setFont(new Font(font, Font.PLAIN, width / 30));
        Lbl_FinalScoreVar.setFont(new Font(font, Font.PLAIN, width / 30));
    }//GEN-LAST:event_formComponentResized

    private void Btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ExitActionPerformed
        dispose();
    }//GEN-LAST:event_Btn_ExitActionPerformed

    private void Btn_PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PlayActionPerformed
        changePanel(Pnl_LettersMenu);
    }//GEN-LAST:event_Btn_PlayActionPerformed

    private void Btn_Cancel_LettersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cancel_LettersActionPerformed
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_Cancel_LettersActionPerformed

    private void Btn_AlphabetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AlphabetActionPerformed

        if (Btn_Alphabet.isSelected()) {
            this.alphabetGame.getDataGame().setGameType("Standard A-Z");
            Btn_Vowels.setSelected(false);
            Btn_Consonnants.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_AlphabetActionPerformed

    private void Btn_VowelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_VowelsActionPerformed

        if (Btn_Vowels.isSelected()) {
            this.alphabetGame.getDataGame().setGameType("Vowels");
            Btn_Alphabet.setSelected(false);
            Btn_Consonnants.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_VowelsActionPerformed

    private void Btn_ConsonnantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ConsonnantsActionPerformed
        // TODO add your handling code here:
        if (Btn_Consonnants.isSelected()) {
            this.alphabetGame.getDataGame().setGameType("Consonnants");
            Btn_Alphabet.setSelected(false);
            Btn_Vowels.setSelected(false);
            Btn_Training.setEnabled(true);
            Btn_Chrono.setEnabled(true);
        } else {
            Btn_Training.setEnabled(false);
            Btn_Chrono.setEnabled(false);
        }
    }//GEN-LAST:event_Btn_ConsonnantsActionPerformed

    private void loadGameFrame(boolean isTrainingMode) {
        alphabetGame.getDataGame().setTrainingMode(isTrainingMode);
        alphabetGame.start();
        changePanel(Pnl_Game);
        if (isTrainingMode) {
            Lbl_ScoreFixed.setText(" ");
            Lbl_ScoreVar.setText(" ");
            Lbl_TimerFixed.setText(" ");
            Lbl_TimerVar.setText(" ");
        } else {
            Lbl_ScoreFixed.setVisible(true);
            Lbl_ScoreVar.setVisible(true);
            Lbl_TimerFixed.setVisible(true);
            Lbl_TimerVar.setVisible(true);
        }
        changeKeyBoard(alphabetGame.getDataGame().getGameType());
    }

    private void changeKeyBoard(int mode) {
        Pnl_KeyBoard.removeAll();
        Pnl_KeyBoard.repaint();
        Pnl_KeyBoard.revalidate();
        switch (mode) {
            case 2:
                vowelsPanel1.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(vowelsPanel1);
                break;
            case 3:
                consonnantsPanel1.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(consonnantsPanel1);
                break;
            default:
                alphabetPanel1.setAlphabetGame(alphabetGame);
                Pnl_KeyBoard.add(alphabetPanel1);
                break;
        }
    }
    private void Btn_TrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TrainingActionPerformed
        loadGameFrame(true);
    }//GEN-LAST:event_Btn_TrainingActionPerformed

    private void Btn_ChronoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ChronoActionPerformed
        loadGameFrame(false);
    }//GEN-LAST:event_Btn_ChronoActionPerformed

    private void Btn_ReturnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ReturnMenuActionPerformed
        alphabetGame.stop();
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_ReturnMenuActionPerformed

    private void Btn_PlayLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PlayLetterActionPerformed
        alphabetGame.playLetter();
    }//GEN-LAST:event_Btn_PlayLetterActionPerformed

    private void Btn_QuitScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_QuitScoreActionPerformed
        changePanel(Pnl_MainMenu);
    }//GEN-LAST:event_Btn_QuitScoreActionPerformed

    //Navigation by Keyboard

    private void Btn_PlayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_PlayKeyPressed
        //Down Arrow:
        if (evt.getKeyCode() == 40) {
            Btn_Exit.requestFocus();
        }
        //Enter
        if (evt.getKeyCode() == 10) {
            Btn_Play.doClick();
        }
    }//GEN-LAST:event_Btn_PlayKeyPressed

    private void Btn_ExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Btn_ExitKeyPressed
        // Top Arrow
        if (evt.getKeyCode() == 38) {
            Btn_Play.requestFocus();
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
            Btn_QuitScore.requestFocus();
        }
    }//GEN-LAST:event_Btn_QuitScoreKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Btn_Alphabet;
    private javax.swing.JButton Btn_Cancel_Letters;
    private javax.swing.JButton Btn_Chrono;
    private javax.swing.JToggleButton Btn_Consonnants;
    private javax.swing.JButton Btn_Exit;
    private javax.swing.JButton Btn_Options;
    private javax.swing.JButton Btn_Play;
    private javax.swing.JButton Btn_PlayLetter;
    private javax.swing.JButton Btn_QuitScore;
    private javax.swing.JButton Btn_ReturnMenu;
    private javax.swing.JButton Btn_Training;
    private javax.swing.JToggleButton Btn_Vowels;
    private javax.swing.JLabel Lbl_Correction;
    private javax.swing.JLabel Lbl_Credits;
    private javax.swing.JLabel Lbl_FinalScoreFix;
    private javax.swing.JLabel Lbl_FinalScoreVar;
    private javax.swing.JLabel Lbl_Letters;
    private javax.swing.JLabel Lbl_Mode;
    private javax.swing.JLabel Lbl_ScoreFixed;
    private javax.swing.JLabel Lbl_ScoreVar;
    private javax.swing.JLabel Lbl_TimerFixed;
    private javax.swing.JLabel Lbl_TimerVar;
    private javax.swing.JLabel Lbl_Title;
    private javax.swing.JPanel Pnl_BotGame;
    private javax.swing.JPanel Pnl_CenGame;
    private javax.swing.JPanel Pnl_Game;
    private javax.swing.JPanel Pnl_Global;
    private javax.swing.JPanel Pnl_KeyBoard;
    private javax.swing.JPanel Pnl_LettersMenu;
    private javax.swing.JPanel Pnl_MainMenu;
    private javax.swing.JPanel Pnl_Score;
    private javax.swing.JPanel Pnl_TopGame;
    private view.Panels.AlphabetPanel alphabetPanel1;
    private view.Panels.ConsonnantsPanel consonnantsPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private view.Panels.VowelsPanel vowelsPanel1;
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

    @Override
    public void updateEndGame(String score) {
        Lbl_FinalScoreVar.setText(score);
        changePanel(Pnl_Score);
    }

    public void updateCorrection(boolean rightOrFalse, char letter) {
        if (rightOrFalse) {
            this.Lbl_Correction.setText(letter + "  Correct!");
        } else {
            this.Lbl_Correction.setText(letter + "  False!");
        }
    }
}
