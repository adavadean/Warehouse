package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProdusBLL;
import model.Client;
import model.Orderr;
import model.Produs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private ClientView c;
    private ProdusView p;
    private OrderView o;
    private ClientBLL cbll;
    private ProdusBLL pbll;
    private OrderBLL obll;

    public Controller(ClientView c, ProdusView p, OrderView o){
        this.c = c;
        this.p = p;
        this.o = o;
        cbll = new ClientBLL();
        pbll = new ProdusBLL();
        obll = new OrderBLL();
        c.addB1Listener(new AddClientB1Listener());
        c.addB2Listener(new AddClientB2Listener());
        c.addB3Listener(new AddClientB3Listener());
        c.addB4Listener(new AddClientB4Listener());
        p.addB1Listenerrr(new AddProdusB1Listener());
        p.addB2Listenerrr(new AddProdusB2Listener());
        p.addB3Listenerrr(new AddProdusB3Listener());
        p.addB4Listenerrr(new AddProdusB4Listener());
        o.addB1Listenerr(new AddOrderB1Listener());
    }

    class AddClientB1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            String i1, i2, i3;
            i1 = c.getT2();
            i2 = c.getT3();
            i3 = c.getT4();
            Client client = new Client(i1,i2,i3);
            cbll.adaugclient(client);
        }
    }
    class AddClientB2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            String i2, i3, i4;
            int i1;
            try{
                i1 = Integer.parseInt(c.getT1());
            } catch (NumberFormatException s) {
                return;
            }
            i2 = c.getT2();
            i3 = c.getT3();
            i4 = c.getT4();
            cbll.updateclient(i1,i2,i3,i4);
        }
    }
    class AddClientB3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            int i1;
            try{
                i1 = Integer.parseInt(c.getT1());
            } catch (NumberFormatException s) {
                return;
            }
            cbll.deleteClient(i1);
        }
    }
    class AddClientB4Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            c.show(cbll.gasiretoticlientii());
        }
    }


    class AddProdusB1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            String i1;
            int i2;
            i1 = p.getTtt2();
            try{
                i2 = Integer.parseInt(p.getTtt3());
            } catch (NumberFormatException s) {
                return;
            }
            Produs product = new Produs(i1,i2);
            pbll.adaugprod(product);
        }
    }
    class AddProdusB2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            String i2;
            int i1, i3;
            try{
                i1 = Integer.parseInt(p.getTtt1());
            } catch (NumberFormatException s) {
                return;
            }
            i2 = p.getTtt2();
            try{
                i3 = Integer.parseInt(p.getTtt3());
            } catch (NumberFormatException s) {
                i3 = -1;
            }
            pbll.updateprod(i1,i2,i3);
        }
    }
    class AddProdusB3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            int i1;
            try{
                i1 = Integer.parseInt(p.getTtt1());
            } catch (NumberFormatException s) {
                return;
            }
            pbll.stergprod(i1);
        }
    }
    class AddProdusB4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            p.show(pbll.gasiretoateprodusele());
        }
    }


    class AddOrderB1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            int i1, i2, i3;
            try{
                i1 = Integer.parseInt(o.getTt1());
            } catch (NumberFormatException s) {
                return;
            }
            try{
                i2 = Integer.parseInt(o.getTt2());
            } catch (NumberFormatException s) {
                return;
            }
            try{
                i3 = Integer.parseInt(o.getTt3());
            } catch (NumberFormatException s) {
                return;
            }
            Orderr order = new Orderr(i1,i2,i3);
            int k = obll.adaugcom(order);
            if(k == -2)
                o.Epuizare();
            else if(k == -1)
                o.NuExista();
            else {
                o.ok();
                Client client = cbll.gasireclientdupaid(i1);
                Produs product = pbll.gasireprodusdupaid(i2);
                o.generarefactura(client.getNume(), product.getNume(), i3);
            }
        }
    }
}
