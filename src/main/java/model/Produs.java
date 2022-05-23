package model;


public class Produs {
    private int id;
    private String nume;
    private int cantitate;

    public Produs(){
    }

    public Produs(int id, String nume, int cantitate)
    {
        this.id = id;
        this.nume = nume;
        this.cantitate = cantitate;
    }

    public Produs(String nume, int cantitate)
    {
        this.nume = nume;
        this.cantitate = cantitate;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getNume()
    {
        return nume;
    }
    public void setNume(String nume)
    {
        this.nume = nume;
    }
    public int getCantitate()
    {
        return cantitate;
    }
    public void setCantitate(int cantitate)
    {
        this.cantitate = cantitate;
    }
}
