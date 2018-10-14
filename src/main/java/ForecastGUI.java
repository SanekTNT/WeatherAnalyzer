import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ForecastGUI extends JFrame{

    private ArrayList<JRadioButton> radioButtonsDays = new ArrayList<JRadioButton>();
    private final ButtonGroup radioGroup = new ButtonGroup();
    private JTable jTable, jTableSinoptik, jTableGismeteo;
    private ForecastTable forecastTable;
    private ForecastTableDayTime forecastTableSinoptik, forecastTableGismeteo;
    private JLabel labelSinoptik = new JLabel("Sinoptik");
    private JLabel labelGismeteo = new JLabel("Gismeteo");

    ForecastGUI(String cityName, int days, ArrayList<String> dates,
                Sinoptik sinoptik, Gismeteo gismeteo){
        super("Forecast for " + cityName);
        if(days <= 8)
            setBounds(200, 50, 850, 470);
        else
            setBounds(200, 50, 850, 490);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        setRadioButtonsDays(days, dates);
        addRadioButtonsDays(container, days);

        forecastTable = new ForecastTable(sinoptik, gismeteo, dates.get(0));
        jTable = forecastTable.getWeatherTable();
        jTable.setDefaultEditor(Object.class, null);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jTable.setPreferredScrollableViewportSize(new Dimension(800, 112));
        container.add(jScrollPane);

        container.add(labelSinoptik);
        forecastTableSinoptik = new ForecastTableDayTime(sinoptik.returnForecasts(), dates.get(0), false);
        jTableSinoptik = forecastTableSinoptik.getWeatherTable();
        jTableSinoptik.setDefaultEditor(Object.class, null);
        JScrollPane jScrollPaneSinoptik = new JScrollPane(jTableSinoptik);
        jTableSinoptik.setPreferredScrollableViewportSize(new Dimension(800, 69));
        container.add(jScrollPaneSinoptik);


        container.add(labelGismeteo);
        forecastTableGismeteo = new ForecastTableDayTime(gismeteo.returnForecasts(), dates.get(0), true);
        jTableGismeteo = forecastTableGismeteo.getWeatherTable();
        jTableGismeteo.setDefaultEditor(Object.class, null);
        JScrollPane jScrollPaneGismeteo = new JScrollPane(jTableGismeteo);
        jTableGismeteo.setPreferredScrollableViewportSize(new Dimension(800, 69));
        container.add(jScrollPaneGismeteo);

        setVisible(true);
    }

    private void addRadioButtonsDays(Container container, int days){
        for(int i=0; i<days; i++){
            radioGroup.add(radioButtonsDays.get(i));
        }
        for(int i=0; i<days; i++){
            container.add(radioButtonsDays.get(i));
        }
    }

    private void setRadioButtonsDays(int days, ArrayList<String> dates){
        ActionListener al = new VoteActionListener();
        radioButtonsDays.add(new JRadioButton(dates.get(0), true));
        radioButtonsDays.get(0).setActionCommand(dates.get(0));
        radioButtonsDays.get(0).addActionListener(al);
        for(int i=1; i<days; i++){
            radioButtonsDays.add(new JRadioButton(dates.get(i), false));
            radioButtonsDays.get(i).setActionCommand(dates.get(i));
            radioButtonsDays.get(i).addActionListener(al);
        }
    }

    class VoteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ex) {
            String newDate = radioGroup.getSelection().getActionCommand();
            forecastTable.changeData(newDate);
            jTable = forecastTable.getWeatherTable();
            forecastTableSinoptik.changeData(newDate);
            jTableSinoptik = forecastTableSinoptik.getWeatherTable();
            forecastTableGismeteo.changeData(newDate);
            jTableGismeteo = forecastTableGismeteo.getWeatherTable();
        }
    }


}
