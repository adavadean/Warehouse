package dao;

import model.Produs;
public class ProdusDAO extends AbstractDAO<Produs>
{
    /**
     * Metoda de adaugare a unui client folosind AbstractDAO
     * @param produs-produsul cu numele si cantitatea
     * @return op de adaugare
     */
    public int adaugarep(Produs produs)
    {
        String numeee = "nume, cantitate";
        String valori2 = "'" + produs.getNume() + "', " + produs.getCantitate();
        return Adaug(numeee,valori2);
    }
    /**
     * Metoda de update a inf  unui produs folosind AbstractDAO
     * @param produs-produsul primit cu numele si cantiate pe care o are
     * @return op de update
     */
    public int updtarep(Produs produs)
    {
        String numeee = "nume = " + "'" + produs.getNume() + "',cantitate = " + produs.getCantitate();
        return Update(numeee,produs.getId());
    }
}
