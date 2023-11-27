package org.example;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIexample {
    private JFrame frame;
    private JTextField urlField;
    private JButton submitButton;
    private JTextArea contentArea;

    public GUIexample() {
        frame = new JFrame("URL Viewer");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        urlField = new JTextField();
        urlField.setBounds(10, 10, 300, 20);
        frame.add(urlField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(320, 10, 70, 20);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                try {
                    Document doc = Jsoup.connect(url).get();
                    contentArea.setText(doc.body().text());
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        frame.add(submitButton);

        contentArea = new JTextArea();
        contentArea.setBounds(10, 40, 380, 300);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        frame.add(contentArea);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIexample();
    }
}
