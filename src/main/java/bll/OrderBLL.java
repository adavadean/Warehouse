package bll;

import dao.OrderDAO;
import model.Orderr;
import model.Produs;
import java.util.NoSuchElementException;

public class OrderBLL {

    /**
     * Metoda de gasire a unui comezni dupa id
     * @param idOrder-id-ul comenzii
     * @return comanda dupa id ul dat
     */
    public Orderr gasirecomandadupaid(int idOrder)
    {
        OrderDAO dao = new OrderDAO();
        Orderr o = dao.GasireId(idOrder);
        if (o == null)
        {
            throw new NoSuchElementException("Comanda cu numarul " + idOrder + " nu s-a gasit!");
        }
        return o;
    }
    /**
     * Metoda de update a unui client dupa id
     * @param comanda-comanda efectiva cu datele din clasa Orderr
     * @return -1,daca nu s-a gasit produsul sau clientul, se face comanda daca s-a gasit si -2 daca nu este in stoc cat s-a comandat
     */
    public int adaugcom(Orderr comanda)
    {
        ClientBLL cb = new ClientBLL();
        try {
            cb.gasireclientdupaid(comanda.getIdClient());
        }catch (NoSuchElementException e){
            return -1;
        }
        ProdusBLL pb = new ProdusBLL();
        Produs p;
        try {
            p = pb.gasireprodusdupaid(comanda.getIdProdus());
        }catch (NoSuchElementException e){
            return -1;
        }
        if(p.getCantitate() >= comanda.getCantitate())
        {
            pb.updateprod(p.getId(),"",p.getCantitate() - comanda.getCantitate());
            OrderDAO dao = new OrderDAO();
            return dao.adaugareo(comanda);
        }
        else
            return -2;
    }
    /**
     * Metoda de stergere a unei comenzi dupa id
     * @param id-id-ul comenzii puse
     * @return -1,daca nu s-a gasit comanda, altfel se sterge comanda
     */
    public int deleteOrder(int id)
    {
        Orderr o;
        try {
            o = gasirecomandadupaid(id);
        }catch (NoSuchElementException e){
            return -1;
        }
        OrderDAO dao = new OrderDAO();
        return dao.Sterg(id);
    }
}
