package dao;

import model.Orderr;
public class OrderDAO extends AbstractDAO<Orderr> {
    /**
     * Metoda de adaugare a unei comenzi folosind AbstractDAO
     * @param comanda-cu informatiile de id de client si produs si cantitatea produsului comandat
     * @return op de adaugare
     */
    public int adaugareo(Orderr comanda)
    {
        String numee = "idclient,idprodus,cantitate";
        String valori = comanda.getIdClient() + "," + comanda.getIdProdus() + "," + comanda.getCantitate();
        return Adaug(numee,valori);
    }
}
