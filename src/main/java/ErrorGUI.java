import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUI extends JFrame{

    private JLabel labelInputValues = new JLabel("Error! :(");
    private JLabel labelInputValues2 = new JLabel("Some input data is incorrect or");
    private JLabel labelInputValues3 = new JLabel("some problems with connection.");
    private JButton buttonOk = new JButton("Ok");

    ErrorGUI(){
        super("Error");
        setBounds(200, 50, 220, 140);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(labelInputValues);
        container.add(labelInputValues2);
        container.add(labelInputValues3);
        container.add(buttonOk);
        setVisible(true);

        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}