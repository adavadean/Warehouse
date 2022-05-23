package bll;

import dao.ClientDAO;
import model.Client;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ClientBLL {

    /**
     * Metoda de update a unui client dupa id
     * @param idClient-id-ul clientului
     * @return clientul gasit dupa id
     */
    public Client gasireclientdupaid(int idClient)
    {
        ClientDAO dao = new ClientDAO();
        Client c = dao.GasireId(idClient);
        if (c == null)
        {
            throw new NoSuchElementException("Clientul cu id  " + idClient + "nu s-a gasit");
        }
        return c;
    }
    /**
     * Metoda de gasire a toti clientilor
     * @return -gasirea lor
     */
    public ArrayList<Client> gasiretoticlientii()
    {
        ClientDAO dao = new ClientDAO();
        return dao.Gasire();
    }
    /**
     * Metoda de adaugare a unui client
     * @param client -clientul ce trebuie inserat
     * @return -agauarea efectiva a clientului
     */
    public int adaugclient(Client client)
    {
        ClientDAO dao = new ClientDAO();
        return dao.adaugare(client);
    }
    /**
     * Metoda de update a datelor a unui client
     * @param id-id-ul clientului
     * @param nume-numele clientului
     * @param adresa-adresa clientului
     * @param email-email-ul clientului
     * @return -update la datele clientului
     */
    public int updateclient(int id, String nume, String adresa, String email)
    {
        Client c;
        try {
            c =gasireclientdupaid(id);
        }catch (NoSuchElementException e){
            return -1;
        }
        if(nume.equals(""))
            nume = c.getNume();
        if(adresa.equals(""))
            adresa = c.getAdresa();
        if(email.equals(""))
            email = c.getEmail();
        ClientDAO dao = new ClientDAO();
        return dao.updtare(new Client(id,nume,adresa,email));
    }
    /**
     * Metoda de stergere a unui client dupa ce-l gaseste cu id-ul sau
     * @param id-id-ul clientului
     * @return -stergere client
     */
    public int deleteClient(int id)
    {
        Client c;
        try {
            c = gasireclientdupaid(id);
        }catch (NoSuchElementException e){
            return -1;
        }
        ClientDAO dao = new ClientDAO();
        return dao.Sterg(id);
    }
}
