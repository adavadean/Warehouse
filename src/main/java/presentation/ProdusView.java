package presentation;

import model.Produs;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProdusView {
    public JLabel lll1 = new JLabel("Id produs: ");
    public JLabel lll2 = new JLabel("Nume: ");
    public JLabel lll3 = new JLabel("Cantitate: ");
    public JTextArea ttt1 = new JTextArea(1, 10);
    public JTextArea ttt2 = new JTextArea(1, 10);
    public JTextArea ttt3 = new JTextArea(1, 10);
    public JButton bbb1 = new JButton("Adaugare produs");
    public JButton bbb2 = new JButton("Editare produs");
    public JButton bbb3 = new JButton("Stergere produs");
    public JButton bbb4 = new JButton("Vizualizare produse");
    private DefaultTableModel model = new DefaultTableModel();
    private JTable t = new JTable(model);
    public ProdusView() {
        JFrame frame = new JFrame("Produse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        JPanel panelll = new JPanel();
        JPanel panelll1 = new JPanel();
        JPanel panelll2 = new JPanel();
        JPanel panelll3 = new JPanel();
        JPanel panelll4 = new JPanel();
        JPanel panelll5 = new JPanel();
        JPanel panelll6 = new JPanel();
        JPanel panelll7 = new JPanel();
        t.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(t);
        FlowLayout flLayout = new FlowLayout();
        GridLayout grLayout = new GridLayout(0, 1, 0, 1);
        panelll.setLayout(new BoxLayout(panelll, BoxLayout.Y_AXIS));
        panelll1.setLayout(flLayout);
        panelll2.setLayout(grLayout);
        panelll3.setLayout(grLayout);
        panelll4.setLayout(grLayout);
        panelll5.setLayout(grLayout);
        panelll6.setLayout(flLayout);
        panelll7.setLayout(flLayout);
        panelll.add(Box.createRigidArea(new Dimension(0, 5)));
        panelll2.add(lll1);
        panelll2.add(lll2);
        panelll2.add(lll3);
        panelll3.add(ttt1);
        panelll3.add(ttt2);
        panelll3.add(ttt3);
        panelll6.add(panelll2);
        panelll6.add(panelll3);
        panelll4.add(bbb1);
        panelll4.add(bbb2);
        panelll5.add(bbb3);
        panelll5.add(bbb4);
        panelll7.add(panelll4);
        panelll7.add(panelll5);
        panelll.add(panelll1);
        panelll.add(Box.createRigidArea(new Dimension(0, 5)));
        panelll.add(panelll6);
        panelll.add(Box.createRigidArea(new Dimension(0, 5)));
        panelll.add(panelll7);
        panelll.add(Box.createRigidArea(new Dimension(0, 5)));
        panelll.add(sp);
        panelll.add( Box.createRigidArea(new Dimension(0,5)) );
        frame.setContentPane(panelll);
        frame.setVisible(true);
    }
    public String getTtt1() {
        return ttt1.getText();
    }
    public String getTtt2() {
        return ttt2.getText();
    }
    public String getTtt3() {
        return ttt3.getText();
    }
    public void show(ArrayList<Produs> product)
    {
        boolean s = true;
        model.setColumnCount(0);
        model.setRowCount(0);
        for(Produs i : product)
        {
            Vector a = new Vector();
            for (Field field : i.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(i);
                    a.add(value);
                    if(s) {
                        model.addColumn(field.getName());
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            s = false;
            model.addRow(a);
        }
    }
    void addB1Listenerrr(ActionListener add) {
        bbb1.addActionListener(add);
    }
    void addB2Listenerrr(ActionListener edit) {
        bbb2.addActionListener(edit);
    }
    void addB3Listenerrr(ActionListener del) {
        bbb3.addActionListener(del);
    }
    void addB4Listenerrr(ActionListener view) {
        bbb4.addActionListener(view);
    }
}
