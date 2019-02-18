/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Win
 */
public class MyTableModel extends DefaultTableModel {

    public MyTableModel() {
        super(new String[]{"Mã nhân viên", "Tên nhân viên", "Đào tạo"}, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 0:
                clazz = Integer.class;
                break;
            case 2:
                clazz = Boolean.class;
                break;
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 2;
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if (aValue instanceof Boolean && column == 2) {
            System.out.println(aValue + Integer.toString(row) + Integer.toString(column));
            Vector rowData = (Vector) getDataVector().get(row);
            rowData.set(2, (boolean) aValue);
            fireTableCellUpdated(row, column);
        }
    }

}
