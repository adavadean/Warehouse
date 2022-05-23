package presentation;

import model.Client;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientView {
    private final JLabel l1 = new JLabel ("Id: ");
    private final JLabel l2 = new JLabel ("Nume: ");
    private final JLabel l3 = new JLabel ("Adresa: ");
    private final JLabel l4 = new JLabel ("Email: ");
    private JTextArea t1 = new JTextArea(1,10);
    private JTextArea t2 = new JTextArea(1,10);
    private JTextArea t3 = new JTextArea(1,10);
    private JTextArea t4 = new JTextArea(1,10);
    private JButton b1 = new JButton("Adaugare client");
    private JButton b2 = new JButton("Editare client");
    private JButton b3 = new JButton("Stergere client");
    private JButton b4 = new JButton("Vizualizare clienti");
    private DefaultTableModel model = new DefaultTableModel();
    private JTable t = new JTable(model);
    public ClientView() {
        JFrame frame = new JFrame ("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        t.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(t);
        FlowLayout flLayout = new FlowLayout();
        GridLayout grLayout = new GridLayout(0,1,0,1);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel1.setLayout(flLayout);
        panel2.setLayout(grLayout);
        panel3.setLayout(grLayout);
        panel4.setLayout(grLayout);
        panel5.setLayout(grLayout);
        panel6.setLayout(flLayout);
        panel7.setLayout(flLayout);
        panel.add(Box.createRigidArea(new Dimension(0,5)) );
        panel2.add(l1);
        panel2.add(l2);
        panel2.add(l3);
        panel2.add(l4);
        panel3.add(t1);
        panel3.add(t2);
        panel3.add(t3);
        panel3.add(t4);
        panel6.add(panel2);
        panel6.add(panel3);
        panel4.add(b1);
        panel4.add(b2);
        panel5.add(b3);
        panel5.add(b4);
        panel7.add(panel4);
        panel7.add(panel5);
        panel.add(panel1);
        panel.add( Box.createRigidArea(new Dimension(0,5)) );
        panel.add(panel6);
        panel.add( Box.createRigidArea(new Dimension(0,5)) );
        panel.add(panel7);
        panel.add( Box.createRigidArea(new Dimension(0,5)) );
        panel.add(sp);
        panel.add( Box.createRigidArea(new Dimension(0,5)) );
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public String getT1() {return t1.getText();}
    public String getT2() {return t2.getText();}
    public String getT3() { return t3.getText();}
    public String getT4() {return t4.getText();}
    public void show(ArrayList<Client> client)
    {
        boolean s = true;
        model.setColumnCount(0);
        model.setRowCount(0);
        for(Client i : client)
        {
            Vector a = new Vector();
            for (Field field : i.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                Object v;
                try {
                    v = field.get(i);
                    a.add(v);
                    if(s)
                    {
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

    void addB1Listener(ActionListener add) {
        b1.addActionListener(add);
    }
    void addB2Listener(ActionListener edit) {
        b2.addActionListener(edit);
    }
    void addB3Listener(ActionListener del) {
        b3.addActionListener(del);
    }
    void addB4Listener(ActionListener view) {
        b4.addActionListener(view);
    }
}
