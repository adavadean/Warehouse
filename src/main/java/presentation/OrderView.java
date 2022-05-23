package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderView {
    public JLabel ll1 = new JLabel("Pune comanda");
    public JLabel ll2 = new JLabel("Id client: ");
    public JLabel ll3 = new JLabel("Id produs: ");
    public JLabel ll4 = new JLabel("Cantitate: ");
    public JTextArea tt1 = new JTextArea(1, 10);
    public JTextArea tt2 = new JTextArea(1, 10);
    public JTextArea tt3 = new JTextArea(1, 10);
    public JTextArea tt4 = new JTextArea(1, 10);
    public JButton bb1 = new JButton("Comanda");

    public OrderView() {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        JPanel panell = new JPanel();
        JPanel panell1 = new JPanel();
        JPanel panell2 = new JPanel();
        JPanel panell3 = new JPanel();
        JPanel panell4 = new JPanel();
        JPanel panell5 = new JPanel();
        JPanel panell6 = new JPanel();
        FlowLayout flLayout = new FlowLayout();
        GridLayout grLayout = new GridLayout(0, 1, 0, 1);
        panell.setLayout(new BoxLayout(panell, BoxLayout.Y_AXIS));
        panell1.setLayout(flLayout);
        panell2.setLayout(grLayout);
        panell3.setLayout(grLayout);
        panell4.setLayout(flLayout);
        panell5.setLayout(flLayout);
        panell6.setLayout(flLayout);
        panell.add(Box.createRigidArea(new Dimension(0, 5)));
        panell1.add(ll1);
        panell2.add(ll2);
        panell2.add(ll3);
        panell2.add(ll4);
        panell3.add(tt1);
        panell3.add(tt2);
        panell3.add(tt3);
        panell5.add(panell2);
        panell5.add(panell3);
        panell4.add(bb1);
        panell6.add(tt4);
        panell.add(panell1);
        panell.add(Box.createRigidArea(new Dimension(0, 5)));
        panell.add(panell5);
        panell.add(Box.createRigidArea(new Dimension(0, 5)));
        panell.add(panell4);
        panell.add(Box.createRigidArea(new Dimension(0, 5)));
        panell.add(panell6);
        panell.add(Box.createRigidArea(new Dimension(0, 5)));
        frame.setContentPane(panell);
        frame.setVisible(true);
    }
    public String getTt1() {return tt1.getText();}
    public String getTt2() {return tt2.getText();}
    public String getTt3() {return tt3.getText();}

    public void generarefactura(String client, String produs, int cantitate)
    {
        try {
            File file = new File("factura.txt");
            file.createNewFile();
            FileWriter f = new FileWriter("factura.txt",false);
            f.write("Clientul " + client + " a comandat " + cantitate + " buc. " + produs + ".\n");
            f.close();
        } catch (IOException e) {
            System.out.println("Eroare!");
        }
    }
    public void ok(){
        tt4.setText("Comanda a fost procesata cu succes!");
    }
    public void NuExista(){
        tt4.setText("Nu exista clientul/produsul!");
    }
    public void Epuizare(){
        tt4.setText("Stoc epuizat!");
    }
    void addB1Listenerr(ActionListener add) {
        bb1.addActionListener(add);
    }
}
