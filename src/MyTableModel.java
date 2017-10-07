import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel() {
      super(new String[]{"Imię i nazwisko", "Płeć", "PESEL","Ubezpieczenie","Badanie"}, 0);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class clazz = String.class;
      switch (columnIndex) {
        case 0:
          clazz = String.class;
          break;
        case 4:
          clazz = Boolean.class;
          break;
      }
      return clazz;
    }
/*
    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 4;
    }
    */
    
    
/*
    @Override
    public void setValueAt(Object aValue, int row, int column) {
      if (aValue instanceof Boolean && column == 4) {
        System.out.println(aValue);
        Vector rowData = (Vector)getDataVector().get(row);
        rowData.set(4, (boolean)aValue);
        fireTableCellUpdated(row, column);
      }
    }
*/
  }
