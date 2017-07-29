/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Panels;

import controller.WordsGame;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author actim
 */
public class WordsPanel extends javax.swing.JPanel {
    final int FONT_SIZE_ELEMENT = 40; 
    private WordsGame wG;
    private String myFont = "ARIAL BLACK";

    public void setWordsGame(WordsGame wG) {
        this.wG = wG;
        this.resizeButtonFont();
    }
    /**
     * Creates new form ConsonnantsPanel
     */
    public WordsPanel() {
        initComponents();
        
        Txt_Word.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (Txt_Word.getText().length() >= 20) 
                {
                    e.consume();
                }
                if (e.getKeyChar() == ' ') {
                    e.consume();
                    wG.playWord();
                }
            }
        });
        keyboardBind(Btn_WordSubmit, KeyEvent.VK_ENTER);
        keyboardBind(Btn_Backspace, KeyEvent.VK_BACK_SPACE);
        
        //Bind keyboard letter to button letter
        keyboardBind(A, KeyEvent.VK_A);
        keyboardBind(B, KeyEvent.VK_B);
        keyboardBind(C, KeyEvent.VK_C);
        keyboardBind(D, KeyEvent.VK_D);
        keyboardBind(E, KeyEvent.VK_E);
        keyboardBind(F, KeyEvent.VK_F);
        keyboardBind(G, KeyEvent.VK_G);
        keyboardBind(H, KeyEvent.VK_H);
        keyboardBind(I, KeyEvent.VK_I);
        keyboardBind(J, KeyEvent.VK_J);
        keyboardBind(K, KeyEvent.VK_K);
        keyboardBind(L, KeyEvent.VK_L);
        keyboardBind(M, KeyEvent.VK_M);
        keyboardBind(N, KeyEvent.VK_N);
        keyboardBind(O, KeyEvent.VK_O);
        keyboardBind(P, KeyEvent.VK_P);
        keyboardBind(Q, KeyEvent.VK_Q);
        keyboardBind(R, KeyEvent.VK_R);
        keyboardBind(S, KeyEvent.VK_S);
        keyboardBind(T, KeyEvent.VK_T);
        keyboardBind(U, KeyEvent.VK_U);
        keyboardBind(V, KeyEvent.VK_V);
        keyboardBind(W, KeyEvent.VK_W);
        keyboardBind(X, KeyEvent.VK_X);
        keyboardBind(Y, KeyEvent.VK_Y);
        keyboardBind(Z, KeyEvent.VK_Z);
        
    }
    public void requestingFocusThreadTxt() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Txt_Word.requestFocus();
            }
        });
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        A = new javax.swing.JButton();
        B = new javax.swing.JButton();
        C = new javax.swing.JButton();
        D = new javax.swing.JButton();
        E = new javax.swing.JButton();
        F = new javax.swing.JButton();
        G = new javax.swing.JButton();
        H = new javax.swing.JButton();
        I = new javax.swing.JButton();
        J = new javax.swing.JButton();
        K = new javax.swing.JButton();
        L = new javax.swing.JButton();
        M = new javax.swing.JButton();
        N = new javax.swing.JButton();
        O = new javax.swing.JButton();
        P = new javax.swing.JButton();
        Q = new javax.swing.JButton();
        R = new javax.swing.JButton();
        S = new javax.swing.JButton();
        T = new javax.swing.JButton();
        U = new javax.swing.JButton();
        V = new javax.swing.JButton();
        W = new javax.swing.JButton();
        X = new javax.swing.JButton();
        Y = new javax.swing.JButton();
        Z = new javax.swing.JButton();
        Txt_Word = new javax.swing.JTextField();
        Btn_WordSubmit = new javax.swing.JButton();
        Btn_Backspace = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 249, 242));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0};
        layout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        layout.rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0};
        setLayout(layout);

        A.setText("A");
        A.setFocusable(false);
        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(A, gridBagConstraints);

        B.setText("B");
        B.setFocusable(false);
        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(B, gridBagConstraints);

        C.setText("C");
        C.setFocusable(false);
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(C, gridBagConstraints);

        D.setText("D");
        D.setFocusable(false);
        D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(D, gridBagConstraints);

        E.setText("E");
        E.setFocusable(false);
        E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(E, gridBagConstraints);

        F.setText("F");
        F.setFocusable(false);
        F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(F, gridBagConstraints);

        G.setText("G");
        G.setFocusable(false);
        G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(G, gridBagConstraints);

        H.setText("H");
        H.setFocusable(false);
        H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(H, gridBagConstraints);

        I.setText("I");
        I.setFocusable(false);
        I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(I, gridBagConstraints);

        J.setText("J");
        J.setFocusable(false);
        J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(J, gridBagConstraints);

        K.setText("K");
        K.setFocusable(false);
        K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(K, gridBagConstraints);

        L.setText("L");
        L.setFocusable(false);
        L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(L, gridBagConstraints);

        M.setText("M");
        M.setFocusable(false);
        M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(M, gridBagConstraints);

        N.setText("N");
        N.setFocusable(false);
        N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(N, gridBagConstraints);

        O.setText("O");
        O.setFocusable(false);
        O.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(O, gridBagConstraints);

        P.setText("P");
        P.setFocusable(false);
        P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(P, gridBagConstraints);

        Q.setText("Q");
        Q.setFocusable(false);
        Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Q, gridBagConstraints);

        R.setText("R");
        R.setFocusable(false);
        R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(R, gridBagConstraints);

        S.setText("S");
        S.setFocusable(false);
        S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(S, gridBagConstraints);

        T.setText("T");
        T.setFocusable(false);
        T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(T, gridBagConstraints);

        U.setText("U");
        U.setFocusable(false);
        U.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(U, gridBagConstraints);

        V.setText("V");
        V.setFocusable(false);
        V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(V, gridBagConstraints);

        W.setText("W");
        W.setFocusable(false);
        W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(W, gridBagConstraints);

        X.setText("X");
        X.setFocusable(false);
        X.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(X, gridBagConstraints);

        Y.setText("Y");
        Y.setFocusable(false);
        Y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Y, gridBagConstraints);

        Z.setText("Z");
        Z.setFocusable(false);
        Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Z, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Txt_Word, gridBagConstraints);

        Btn_WordSubmit.setText("Submit");
        Btn_WordSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_WordSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Btn_WordSubmit, gridBagConstraints);

        Btn_Backspace.setText("<-");
        Btn_Backspace.setFocusable(false);
        Btn_Backspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BackspaceBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(Btn_Backspace, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        Txt_Word.requestFocus();
    }//GEN-LAST:event_BActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        this.resizeButtonFont();
    }//GEN-LAST:event_formComponentResized

    private void Btn_BackspaceBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BackspaceBActionPerformed
        // TODO add your handling code here:
        Txt_Word.requestFocus();
    }//GEN-LAST:event_Btn_BackspaceBActionPerformed

    private void Btn_WordSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_WordSubmitActionPerformed
        // TODO add your handling code here:
        if(this.wG.sendAnswer(Txt_Word.getText())) this.Txt_Word.setText("");
    }//GEN-LAST:event_Btn_WordSubmitActionPerformed
    private void resizeButtonFont() {
        int width = this.getWidth();
        Txt_Word.setFont(new Font(myFont, Font.PLAIN, width / (FONT_SIZE_ELEMENT-10)));
        Btn_WordSubmit.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT)); 
        A.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        B.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        C.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        D.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        E.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        F.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        G.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        H.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        I.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        J.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        K.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        L.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        M.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        N.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        O.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        P.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        Q.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        R.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        S.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        T.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        U.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        V.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        W.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        X.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        Y.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
        Z.setFont(new Font(myFont, Font.PLAIN, width / FONT_SIZE_ELEMENT));
    }
    
        /**
     * @return the wG
     */
    public WordsGame getWordsGame() {
        return wG;
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A;
    private javax.swing.JButton B;
    private javax.swing.JButton Btn_Backspace;
    private javax.swing.JButton Btn_WordSubmit;
    private javax.swing.JButton C;
    private javax.swing.JButton D;
    private javax.swing.JButton E;
    private javax.swing.JButton F;
    private javax.swing.JButton G;
    private javax.swing.JButton H;
    private javax.swing.JButton I;
    private javax.swing.JButton J;
    private javax.swing.JButton K;
    private javax.swing.JButton L;
    private javax.swing.JButton M;
    private javax.swing.JButton N;
    private javax.swing.JButton O;
    private javax.swing.JButton P;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton S;
    private javax.swing.JButton T;
    private javax.swing.JTextField Txt_Word;
    private javax.swing.JButton U;
    private javax.swing.JButton V;
    private javax.swing.JButton W;
    private javax.swing.JButton X;
    private javax.swing.JButton Y;
    private javax.swing.JButton Z;
    // End of variables declaration//GEN-END:variables
}
