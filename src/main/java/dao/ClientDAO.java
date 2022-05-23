package dao;

import model.Client;
public class ClientDAO extends AbstractDAO<Client>
{
    /**
     * Metoda de adaugare a unui client folosind AbstractDAO
     * @param client -clientul dat
     * @return op de adaugare
     */
    public int adaugare(Client client)
    {
        String nume = "nume,adresa,email";
        String valori1 = "'" + client.getNume() + "','" + client.getAdresa() + "','" + client.getEmail() + "'";
        return Adaug(nume,valori1);
    }
    /**
     * Metoda de update a inf unui client folosind AbstractDAO
     * @param client-clientul dat
     * @return op de update
     */
    public int updtare(Client client)
    {
        String nume = "nume = '" + client.getNume() + "',adresa = '" + client.getAdresa() + "',email = '" + client.getEmail() + "'";
        return Update(nume,client.getId());
    }
}

