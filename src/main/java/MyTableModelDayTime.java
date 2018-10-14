import javax.swing.table.DefaultTableModel;

class MyTableModelDayTime extends DefaultTableModel{

    private Object[][] data;

    MyTableModelDayTime(Object[][] data){
        this.data = data;
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return 3;
    }
}