package com.company;

import Iris.Iris;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean run = true;

    public static void main(String[] args) throws IOException {
//        String pathTest = "src/Iris/iris_test.txt";
//        String pathTraining = "src/Iris/iris_training.txt";
        SwingUtilities.invokeLater(Main::new);
    }

    public Main(){
        JFrame frame = new JFrame("KNN");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(200, 200);
        JPanel jPanel = new JPanel();

        jPanel.setPreferredSize(new Dimension(400, 200));
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        jPanel.setBackground(Color.magenta);
        JLabel jLabel = new JLabel("key value \n Enter Testing Data \n Enter Training data");
        JTextField jTextField = new JTextField(50);
        JTextField jTextField1 = new JTextField(50);
        JTextField jTextField2 = new JTextField(50);
        JTextField jTextField3 = new JTextField(50);
        JButton button = new JButton("Calcres");
        JButton jButton = new JButton("add your flower");
        jButton.setBackground(Color.magenta.darker());
        button.setBackground(Color.magenta);
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jButton);
        jPanel.add(button);
//sdsd

        jButton.addActionListener(e ->{
            try {
               outPut(flower(jTextField3.getText(),jTextField.getText()),jPanel,frame);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button.addActionListener(e -> {

            String s = null;
            try {
                s = start(jTextField.getText(), jTextField1.getText(), jTextField2.getText());
            } catch (Exception ex) {
                s = "Something went wrong with input data";
            }
            outPut(s,jPanel,frame);
        });


        frame.setContentPane(jPanel);
        frame.pack();
    }

    public static void outPut(String string, JPanel jPanel,JFrame jFrame) {

        jPanel.removeAll();
        jPanel.revalidate();
        jPanel.setLayout(new GridLayout(3, 1));
        JLabel jLabel = new JLabel(string);
        jPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        jPanel.setBackground(Color.magenta);
        jPanel.add(jLabel);
        JButton jButton = new JButton("Continue");
        jButton.setBackground(Color.magenta.darker());
        jButton.addActionListener(e -> {
            jFrame.dispose();
           new Main();
        });
        JButton jButton1 = new JButton("Exit");
        jButton1.setBackground(Color.magenta.darker());
        jButton1.addActionListener(e -> {
            System.exit(0);
        });
        if (!string.equals("Something went wrong with input data"))
        jPanel.add(jButton);
        jPanel.add(jButton1);
    }

    public static String flower(String s,String key) throws IOException {
          String[] strings = s.split(" ");
          double[] doubles = new double[strings.length-1];
          for(int i = 0; i<strings.length-1; i++){
              doubles[i] = Double.parseDouble(strings[i]);
          }
          KNN knn = new KNN();
         Iris iris = new Iris(doubles,strings[strings.length-1]);
        List<Iris> irises = new ArrayList<Iris>();
        irises.add(iris);
return knn.clacOne(irises,knn.readTraining("src/Iris/iris_training.txt"),Integer.parseInt(key));
    }

    public static String start(String key, String pathTest, String pathTraining) throws Exception {
        int correctlyClassified = 0;
        int statistics = 0;
        KNN knn = new KNN();
        boolean[] arr = new boolean[0];
        arr = knn.calculating(knn.readTest(pathTest), knn.readTraining(pathTraining), Integer.parseInt(key));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                correctlyClassified++;
            }
        }
        statistics = (correctlyClassified * 100) / arr.length;
        System.out.println("correctlyClassified: " + correctlyClassified);
        System.out.println("percent: " + statistics + "%");
        String string = "correctlyClassified: " + correctlyClassified + " percent: " + statistics;
        return string;
    }
}
