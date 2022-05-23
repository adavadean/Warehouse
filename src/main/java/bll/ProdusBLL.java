package bll;

import dao.ProdusDAO;
import model.Produs;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProdusBLL {

    /**
     * Metoda de gasire a produsului dupa id
     * @param idProdus-id produs
     * @return un produs gasit dupa un id dat
     */
    public Produs gasireprodusdupaid(int idProdus)
    {
        ProdusDAO dao = new ProdusDAO();
        Produs p = new Produs();
        p=dao.GasireId(idProdus);
        if (p == null)
        {
            throw new NoSuchElementException("Produsul cu numarul " + idProdus + " nu s-a gasit!");
        }
        return p;
    }
    /**
     * Metoda de gasire a tuturor produselor
     * @return lista cu produsele
     */
    public ArrayList<Produs> gasiretoateprodusele()
    {
        ProdusDAO dao = new ProdusDAO();
        return dao.Gasire();
    }

    public int adaugprod(Produs produs)
    {
        ProdusDAO dao = new ProdusDAO();
        return dao.adaugarep(produs);
    }

    public int updateprod(int id, String nume, int cantitate)
    {
        Produs p;
        try {
            p = gasireprodusdupaid(id);
        }catch (NoSuchElementException e){
            return -1;
        }
        if(nume.equals(""))
            nume = p.getNume();
        if(cantitate == -1)
            cantitate = p.getCantitate();
        ProdusDAO da = new ProdusDAO();
        return da.updtarep(new Produs(id,nume,cantitate));
    }

    public int stergprod(int id)
    {
        Produs p;
        try {
            p = gasireprodusdupaid(id);
        }catch (NoSuchElementException e){
            return -1;
        }
        ProdusDAO dao = new ProdusDAO();
        return dao.Sterg(id);
    }
}