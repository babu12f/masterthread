package com.babor.threadInSwing;

import javax.swing.*;

public class ThreadInSwingDemon {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadInSwing("Babor");
        });
    }

}
