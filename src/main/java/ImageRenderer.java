import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class ImageRenderer extends DefaultTableCellRenderer {

    private JLabel lbl = new JLabel();
    private Object[][] data;
    private String picture;

    ImageRenderer(Object[][] data, String picture){
        this.picture = picture;
        this.data = data;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if(row == 1) {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(picture));
                lbl.setText("");
                lbl.setIcon(icon);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            lbl.setText((String) data[row][column]);
            lbl.setIcon(null);
        }
        return lbl;
    }
}