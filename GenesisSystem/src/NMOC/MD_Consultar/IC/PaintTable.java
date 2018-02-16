package NMOC.MD_Consultar.IC;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @author Mouse
 */
public class PaintTable extends DefaultTableCellRenderer
{
    private int columna_patron;
    
    public PaintTable(int Colpatron)
    {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if( table.getValueAt(row,columna_patron).equals("VENCIDO") )
        {
            setBackground(Color.red);
        }
        
        if( table.getValueAt(row,columna_patron).equals("VIGENTE"))
        {
            setBackground(Color.green);
        }
        
        if( table.getValueAt(row,columna_patron).equals("POR VENCER"))
        {
            setBackground(Color.yellow);
        }
        
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }

}
