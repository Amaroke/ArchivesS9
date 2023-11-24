import javax.swing.*;
import java.awt.*;

public class Exercice4 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Exercice4::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("City Information App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField northField = new JTextField("44.1", 10);
        JTextField southField = new JTextField("-9.9", 10);
        JTextField eastField = new JTextField("-22.4", 10);
        JTextField westField = new JTextField("55.2", 10);

        JRadioButton jsonRadioButton = new JRadioButton("JSON");
        JRadioButton xmlRadioButton = new JRadioButton("XML");
        ButtonGroup dataTypeGroup = new ButtonGroup();
        dataTypeGroup.add(jsonRadioButton);
        dataTypeGroup.add(xmlRadioButton);
        jsonRadioButton.setSelected(true);

        JButton submitButton = new JButton("Submit");
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("North:"), gbc);
        gbc.gridx = 1;
        panel.add(northField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("South:"), gbc);
        gbc.gridx = 1;
        panel.add(southField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("East:"), gbc);
        gbc.gridx = 1;
        panel.add(eastField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("West:"), gbc);
        gbc.gridx = 1;
        panel.add(westField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(jsonRadioButton, gbc);
        gbc.gridx = 1;
        panel.add(xmlRadioButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(resultArea, gbc);

        submitButton.addActionListener(e -> {
            double north = Double.parseDouble(northField.getText());
            double south = Double.parseDouble(southField.getText());
            double east = Double.parseDouble(eastField.getText());
            double west = Double.parseDouble(westField.getText());

            String dataType = jsonRadioButton.isSelected() ? "JSON" : "XML";
            callWebService call = new callWebService();
            String urlJSON = "http://api.geonames.org/citiesJSON";
            String urlXML = "http://api.geonames.org/cities";

            System.out.println("----------------------------");
            System.out.println("WebService Type : REST");
            System.out.println("Url for JSON data : " + urlJSON);
            System.out.println("Url for XML data : " + urlXML);

            System.out.println("Parameters : ");
            System.out.println("north, south, east, west : " + north + ", " + south + ", " + east + ", " + west);

            if(dataType.equals("JSON")) {
                call.initializeService(urlJSON + "?north=" + north + "&south=" + south + "&east=" + east + "&west=" + west + "&username=RANDOMACCOUNT1234");
                String jsonRepsonse = call.callJSONPlaceholderService("posts", 1);
                System.out.println("Result :" + jsonRepsonse );

                resultArea.setText(jsonRepsonse);
            } else {
                call.initializeService(urlXML + "?north=" + north + "&south=" + south + "&east=" + east + "&west=" + west + "&username=RANDOMACCOUNT1234");
                String xmlRepsonse = call.callJSONPlaceholderService("posts", 2);
                System.out.println("Result :" + xmlRepsonse );

                resultArea.setText(xmlRepsonse);
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
