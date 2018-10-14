import javax.swing.table.DefaultTableModel;

class MyTableModel extends DefaultTableModel{

    private Object[][] data;

    MyTableModel(Object[][] data){
        this.data = data;
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return 4;
    }
}