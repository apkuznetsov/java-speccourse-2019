package com.company;

import javax.swing.*;

public class Frame extends javax.swing.JFrame {
    private static final String PLAF_1 = "javax.swing.plaf.metal.MetalLookAndFeel";
    private static final String PLAF_2 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static final String PLAF_3 = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    
    private static final String ACTION_COMMAND_1 = "plaf1";
    private static final String ACTION_COMMAND_2 = "plaf2";
    private static final String ACTION_COMMAND_3 = "plaf3";

    private int estimatedNumb;
    private Guesser guesser;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chooseStyleLabel;
    private javax.swing.ButtonGroup chooseStyleRadioButtonGroup;
    private javax.swing.JButton guessLessButton;
    private javax.swing.JButton guessMoreButton;
    private javax.swing.JLabel maxLabel;
    private javax.swing.JSpinner maxNumbSpinner;
    private javax.swing.JLabel minAndMaxLabel;
    private javax.swing.JLabel minLabel;
    private javax.swing.JSpinner minNumbSpinner;
    private javax.swing.JRadioButton plafRadioButton1;
    private javax.swing.JRadioButton plafRadioButton2;
    private javax.swing.JRadioButton plafRadioButton3;
    private javax.swing.JScrollPane programScrollPane;
    private javax.swing.JTextArea programTextArea;
    private javax.swing.JButton startButton;
    private javax.swing.JButton youGuessedButton;
    // End of variables declaration//GEN-END:variables

    public Frame() {
        initComponents();
        addRadioButtonsToGroup();
        changePlaf(PLAF_1);

        createGuesser();
        enableStarting();
        disableGuessing();
    }

    private void addRadioButtonsToGroup() {
        chooseStyleRadioButtonGroup.add(plafRadioButton1);
        chooseStyleRadioButtonGroup.add(plafRadioButton2);
        chooseStyleRadioButtonGroup.add(plafRadioButton3);

        plafRadioButton1.setSelected(true);
    }

    private void changePlaf(String actionCommand) {
        try {
            String plafName = PLAF_1;
            switch (actionCommand) {
                case ACTION_COMMAND_1:
                    plafName = PLAF_1;
                    break;
                case ACTION_COMMAND_2:
                    plafName = PLAF_2;
                    break;
                case ACTION_COMMAND_3:
                    plafName = PLAF_3;
                    break;
                default:
                    break;
            }

            UIManager.setLookAndFeel(plafName);
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        } catch (Exception exc) {
            programTextArea.append(exc.getMessage());
            programTextArea.append(System.lineSeparator());
        }
    }

    private void createGuesser() {
        int min = (int) minNumbSpinner.getValue();
        int max = (int) maxNumbSpinner.getValue();
        guesser = new Guesser(min, max);
    }

    private void guess() {
        disableStarting();
        enableGuessing();

        try {
            guesser.setMinNumb((int) minNumbSpinner.getValue());
            guesser.setMaxNumb((int) maxNumbSpinner.getValue());
        } catch (Exception exc) {
            programTextArea.append(exc.getMessage());
            programTextArea.append(System.lineSeparator());
            disableGuessing();
            enableStarting();
            return;
        }

        estimatedNumb = guesser.guess();

        programTextArea.append("я думаю, что это ... ");
        programTextArea.append(Integer.toString(estimatedNumb));
        programTextArea.append(System.lineSeparator());
    }

    private void disableStarting() {
        minNumbSpinner.setEnabled(false);
        maxNumbSpinner.setEnabled(false);
        startButton.setEnabled(false);
    }

    private void enableGuessing() {
        youGuessedButton.setEnabled(true);
        guessLessButton.setEnabled(true);
        guessMoreButton.setEnabled(true);
    }

    private void youGuessed() {
        enableStarting();
        disableGuessing();
        programTextArea.append("Я угадал! Вы загадали ... " + Integer.toString(estimatedNumb));
        programTextArea.append(System.lineSeparator());
        programTextArea.append("Хотите сыграть ещё раз? Нажмите НАЧАТЬ.");
        programTextArea.append(System.lineSeparator());
    }

    private void enableStarting() {
        minNumbSpinner.setEnabled(true);
        maxNumbSpinner.setEnabled(true);
        startButton.setEnabled(true);
    }

    private void disableGuessing() {
        youGuessedButton.setEnabled(false);
        guessLessButton.setEnabled(false);
        guessMoreButton.setEnabled(false);
    }

    private void guessLess() {
        if (guesser.getMinNumb() == guesser.getMaxNumb()) {
            youAreCheater();
        } else {
            final int newMax = estimatedNumb - 1;
            maxNumbSpinner.setValue((Object) newMax);
            guess();
        }
    }

    private void youAreCheater() {
        programTextArea.append("Вы жульничаете! Я уже угадал число.");
        programTextArea.append(System.lineSeparator());
        programTextArea.append("Хотите сыграть ещё раз? Нажмите НАЧАТЬ.");
        programTextArea.append(System.lineSeparator());
        disableGuessing();
        enableStarting();
    }

    private void guessMore() {
        if (guesser.getMinNumb() == guesser.getMaxNumb()) {
            youAreCheater();
        } else {
            final int newMin = estimatedNumb + 1;
            minNumbSpinner.setValue((Object) newMin);
            guess();
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
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

        chooseStyleRadioButtonGroup = new javax.swing.ButtonGroup();
        minAndMaxLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        maxLabel = new javax.swing.JLabel();
        programScrollPane = new javax.swing.JScrollPane();
        programTextArea = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();
        youGuessedButton = new javax.swing.JButton();
        guessLessButton = new javax.swing.JButton();
        guessMoreButton = new javax.swing.JButton();
        plafRadioButton1 = new javax.swing.JRadioButton();
        plafRadioButton2 = new javax.swing.JRadioButton();
        plafRadioButton3 = new javax.swing.JRadioButton();
        chooseStyleLabel = new javax.swing.JLabel();
        minNumbSpinner = new javax.swing.JSpinner();
        maxNumbSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        minAndMaxLabel.setText("значения диапазона:");

        minLabel.setText("минимальное");

        maxLabel.setText("максимальное");

        programTextArea.setColumns(20);
        programTextArea.setRows(5);
        programScrollPane.setViewportView(programTextArea);

        startButton.setText("НАЧАТЬ");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        youGuessedButton.setText("угадал");
        youGuessedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                youGuessedButtonActionPerformed(evt);
            }
        });

        guessLessButton.setText("меньше");
        guessLessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessLessButtonActionPerformed(evt);
            }
        });

        guessMoreButton.setText("больше");
        guessMoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessMoreButtonActionPerformed(evt);
            }
        });

        plafRadioButton1.setText(PLAF_1);
        plafRadioButton1.setActionCommand(ACTION_COMMAND_1);
        plafRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plafRadioButton1ActionPerformed(evt);
            }
        });

        plafRadioButton2.setText(PLAF_2);
        plafRadioButton2.setActionCommand(ACTION_COMMAND_2);
        plafRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plafRadioButton1ActionPerformed(evt);
            }
        });

        plafRadioButton3.setText(PLAF_3);
        plafRadioButton3.setActionCommand(ACTION_COMMAND_3);
        plafRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plafRadioButton1ActionPerformed(evt);
            }
        });

        chooseStyleLabel.setText("стиль:");
        chooseStyleLabel.setToolTipText("");

        minNumbSpinner.setModel(new SpinnerNumberModel(Guesser.DEFAULT_MIN_NUMB, Guesser.DEFAULT_MIN_NUMB, Guesser.DEFAULT_MAX_NUMB, 1));

        maxNumbSpinner.setModel(new SpinnerNumberModel(Guesser.DEFAULT_MAX_NUMB, Guesser.DEFAULT_MIN_NUMB, Guesser.DEFAULT_MAX_NUMB, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(minAndMaxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(youGuessedButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(guessLessButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(guessMoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(plafRadioButton1)
                                                        .addComponent(plafRadioButton2)
                                                        .addComponent(plafRadioButton3)
                                                        .addComponent(chooseStyleLabel))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(minLabel)
                                                        .addComponent(maxLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(minNumbSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(maxNumbSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(programScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(minAndMaxLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(minLabel)
                                                        .addComponent(minNumbSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(maxLabel)
                                                        .addComponent(maxNumbSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(startButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(youGuessedButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(guessLessButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(guessMoreButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                                .addComponent(chooseStyleLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(plafRadioButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(plafRadioButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(plafRadioButton3))
                                        .addComponent(programScrollPane))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        guess();
    }//GEN-LAST:event_startButtonActionPerformed

    private void youGuessedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_youGuessedButtonActionPerformed
        youGuessed();
    }//GEN-LAST:event_youGuessedButtonActionPerformed

    private void guessLessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessLessButtonActionPerformed
        guessLess();
    }//GEN-LAST:event_guessLessButtonActionPerformed

    private void guessMoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessMoreButtonActionPerformed
        guessMore();
    }//GEN-LAST:event_guessMoreButtonActionPerformed

    private void plafRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plafRadioButton1ActionPerformed
        changePlaf(chooseStyleRadioButtonGroup.getSelection().getActionCommand());
    }//GEN-LAST:event_plafRadioButton1ActionPerformed
}
