package ui;
import domain.Dishes;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DishTableModel extends DefaultTableModel
{
    private final List<Dishes> DishList;
     
    private final String[] columnNames = new String[] {
    		"RestName", "Distance", "DishPrice", "Rating", "Quantity", "DishName", "orderquantity"
    };
    private final Class[] columnClass = new Class[] {
        Integer.class, String.class, Double.class, Boolean.class
    };
 
    public DishTableModel(List<Dishes> DishList)
    {
        this.DishList = DishList;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return DishList.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Dishes row = DishList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getRestName();
        }
        else if(1 == columnIndex) {
            return row.getDishName();
        }
        else if(2 == columnIndex) {
            return row.getDishPrice();
        }
        else if(3 == columnIndex) {
            return row.getOrderQuantity();
        }
        return null;
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Dishes row = DishList.get(rowIndex);
        if(7 == columnIndex) {
            row.setOrderQuantity((Integer) aValue);
        }
        
    }
 
}




