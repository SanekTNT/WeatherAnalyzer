import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

    private JTextField inputNameOfCity = new JTextField("", 6);
    private JTextField inputNameOfCountry = new JTextField("", 6);
    private JTextField inputAmountDays = new JTextField("", 5);
    private JLabel labelInputNameOfCity = new JLabel("Enter name of city: ");
    private JLabel labelInputNameOfCountry = new JLabel("Enter name of country: ");
    private JLabel labelInputAmountDays = new JLabel("Enter amount of days (max 10): ");
    private JButton buttonWeatherForecast = new JButton("Weather forecast");

    GUI(){
        super("Weather");
        setBounds(200, 50, 300, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(labelInputNameOfCity);
        container.add(inputNameOfCity);
        container.add(labelInputNameOfCountry);
        container.add(inputNameOfCountry);
        container.add(labelInputAmountDays);
        container.add(inputAmountDays);
        container.add(buttonWeatherForecast);
        setVisible(true);

        buttonWeatherForecast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int days = Integer.parseInt(inputAmountDays.getText());
                    if(days > 0 && days < 11) {
                        Sinoptik sinoptik =
                                new Sinoptik(inputNameOfCity.getText(), inputNameOfCountry.getText(), days);
                        Gismeteo gismeteo =
                                new Gismeteo(inputNameOfCity.getText(), inputNameOfCountry.getText(), days);
                        new ForecastGUI(inputNameOfCity.getText() + ", " + inputNameOfCountry.getText(), days,
                                new Dates().datesForForecast(days), sinoptik, gismeteo);
                    }
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                    new ErrorGUI();
                }
            }
        });
    }
}
