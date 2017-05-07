/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Panels;

import controller.AlphabetGame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.KeyStroke;

/**
 *
 * @author actim
 */
public class ConsonnantsPanel extends javax.swing.JPanel {

    private AlphabetGame aG;
    private String myFont = "ARIAL BLACK";
    public void setAlphabetGame(AlphabetGame aG) {
        this.aG = aG;
        this.resizeButtonFont();
    }

    /**
     * Creates new form ConsonnantsPanel
     */
    public ConsonnantsPanel() {
        initComponents();
        
        //Bind keyboard letter to button letter
        keyboardBind(B, KeyEvent.VK_B);
        keyboardBind(C, KeyEvent.VK_C);
        keyboardBind(D, KeyEvent.VK_D);
        keyboardBind(F, KeyEvent.VK_F);
        keyboardBind(G, KeyEvent.VK_G);
        keyboardBind(H, KeyEvent.VK_H);
        keyboardBind(J, KeyEvent.VK_J);
        keyboardBind(K, KeyEvent.VK_K);
        keyboardBind(L, KeyEvent.VK_L);
        keyboardBind(M, KeyEvent.VK_M);
        keyboardBind(N, KeyEvent.VK_N);
        keyboardBind(P, KeyEvent.VK_P);
        keyboardBind(Q, KeyEvent.VK_Q);
        keyboardBind(R, KeyEvent.VK_R);
        keyboardBind(S, KeyEvent.VK_S);
        keyboardBind(T, KeyEvent.VK_T);
        keyboardBind(V, KeyEvent.VK_V);
        keyboardBind(W, KeyEvent.VK_W);
        keyboardBind(X, KeyEvent.VK_X);
        keyboardBind(Z, KeyEvent.VK_Z);
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

        B = new javax.swing.JButton();
        C = new javax.swing.JButton();
        D = new javax.swing.JButton();
        F = new javax.swing.JButton();
        G = new javax.swing.JButton();
        H = new javax.swing.JButton();
        J = new javax.swing.JButton();
        K = new javax.swing.JButton();
        L = new javax.swing.JButton();
        M = new javax.swing.JButton();
        N = new javax.swing.JButton();
        P = new javax.swing.JButton();
        Q = new javax.swing.JButton();
        R = new javax.swing.JButton();
        S = new javax.swing.JButton();
        T = new javax.swing.JButton();
        V = new javax.swing.JButton();
        W = new javax.swing.JButton();
        X = new javax.swing.JButton();
        Z = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 249, 242));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new java.awt.GridLayout(2, 10));

        B.setText("B");
        B.setFocusable(false);
        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(B);

        C.setText("C");
        C.setFocusable(false);
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(C);

        D.setText("D");
        D.setFocusable(false);
        D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(D);

        F.setText("F");
        F.setFocusable(false);
        F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(F);

        G.setText("G");
        G.setFocusable(false);
        G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(G);

        H.setText("H");
        H.setFocusable(false);
        H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(H);

        J.setText("J");
        J.setFocusable(false);
        J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(J);

        K.setText("K");
        K.setFocusable(false);
        K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(K);

        L.setText("L");
        L.setFocusable(false);
        L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(L);

        M.setText("M");
        M.setFocusable(false);
        M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(M);

        N.setText("N");
        N.setFocusable(false);
        N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(N);

        P.setText("P");
        P.setFocusable(false);
        P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(P);

        Q.setText("Q");
        Q.setFocusable(false);
        Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(Q);

        R.setText("R");
        R.setFocusable(false);
        R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(R);

        S.setText("S");
        S.setFocusable(false);
        S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(S);

        T.setText("T");
        T.setFocusable(false);
        T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(T);

        V.setText("V");
        V.setFocusable(false);
        V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(V);

        W.setText("W");
        W.setFocusable(false);
        W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(W);

        X.setText("X");
        X.setFocusable(false);
        X.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(X);

        Z.setText("Z");
        Z.setFocusable(false);
        Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });
        add(Z);
    }// </editor-fold>//GEN-END:initComponents

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        char letter = evt.getActionCommand().toString().charAt(0);
        aG.sendAnswer(letter);
    }//GEN-LAST:event_BActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        this.resizeButtonFont();
    }//GEN-LAST:event_formComponentResized

    private void resizeButtonFont() {
        int width = this.getWidth();
        B.setFont(new Font(myFont, Font.PLAIN, width / 40));
        C.setFont(new Font(myFont, Font.PLAIN, width / 40));
        D.setFont(new Font(myFont, Font.PLAIN, width / 40));
        F.setFont(new Font(myFont, Font.PLAIN, width / 40));
        G.setFont(new Font(myFont, Font.PLAIN, width / 40));
        H.setFont(new Font(myFont, Font.PLAIN, width / 40));
        J.setFont(new Font(myFont, Font.PLAIN, width / 40));
        K.setFont(new Font(myFont, Font.PLAIN, width / 40));
        L.setFont(new Font(myFont, Font.PLAIN, width / 40));
        M.setFont(new Font(myFont, Font.PLAIN, width / 40));
        N.setFont(new Font(myFont, Font.PLAIN, width / 40));
        P.setFont(new Font(myFont, Font.PLAIN, width / 40));
        Q.setFont(new Font(myFont, Font.PLAIN, width / 40));
        R.setFont(new Font(myFont, Font.PLAIN, width / 40));
        S.setFont(new Font(myFont, Font.PLAIN, width / 40));
        T.setFont(new Font(myFont, Font.PLAIN, width / 40));
        V.setFont(new Font(myFont, Font.PLAIN, width / 40));
        W.setFont(new Font(myFont, Font.PLAIN, width / 40));
        X.setFont(new Font(myFont, Font.PLAIN, width / 40));
        Z.setFont(new Font(myFont, Font.PLAIN, width / 40));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B;
    private javax.swing.JButton C;
    private javax.swing.JButton D;
    private javax.swing.JButton F;
    private javax.swing.JButton G;
    private javax.swing.JButton H;
    private javax.swing.JButton J;
    private javax.swing.JButton K;
    private javax.swing.JButton L;
    private javax.swing.JButton M;
    private javax.swing.JButton N;
    private javax.swing.JButton P;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton S;
    private javax.swing.JButton T;
    private javax.swing.JButton V;
    private javax.swing.JButton W;
    private javax.swing.JButton X;
    private javax.swing.JButton Z;
    // End of variables declaration//GEN-END:variables
}
