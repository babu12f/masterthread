package com.babor.threadInSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThreadInSwing extends JFrame {

    private JLabel progressLabel = new JLabel("0");
    private JLabel statusLabel = new JLabel("Task not completed.");
    private JButton startButton = new JButton("Start");

    public ThreadInSwing(String title) {
        super(title);

        setLayout(new GridBagLayout());

        GridBagConstraints bagConstraints = new GridBagConstraints();

        bagConstraints.fill = GridBagConstraints.NONE;

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        add(progressLabel, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        add(statusLabel, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 2;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        add(startButton, bagConstraints);

        startButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start() {
        System.out.println("click");

        /*SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i <= 50; i++) {
                    Thread.sleep(100);
                    System.out.println("Printing form background: " + i);
                }
                return null;
            }
        };
        worker.execute();*/

        SwingWorker<Double, Integer> worker = new SwingWorker<Double, Integer>() {
            @Override
            protected Double doInBackground() throws Exception {
                for (int i = 1; i <= 50; i++) {
                    Thread.sleep(100);
                    System.out.println("Printing form background: " + i);
                    //progressLabel.setText(i+"");

                    publish(i);
                }
                return 2.99;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int val = chunks.get(chunks.size() - 1);
                progressLabel.setText("" + val);
            }

            @Override
            protected void done() {
                System.out.println("Completed Background Processing");

                try {
                    Double returnValue = get();
                    statusLabel.setText("Task is Completed: and return result: " + returnValue);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
