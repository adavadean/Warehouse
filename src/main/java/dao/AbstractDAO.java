package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;


public class AbstractDAO<T>
{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> tip;

    @SuppressWarnings("unchecked")
    public AbstractDAO()
    {
        this.tip = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Metoda de creare a lui select
     * @param field
     * @return string cu select
     */
    private String creareSelect(String field)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(tip.getSimpleName());
        if(field!=null)
        {
            sb.append(" WHERE ").append(field).append("=?");
        }
        return sb.toString();
    }
    /**
     * Metoda de creare a lui select all
     * @return string cu select
     */

    private String creareSelectTot()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(tip.getSimpleName());
        return sb.toString();
    }
    /**
     * Metoda de creare a lui insert
     * @param nume
     * @param valori
     * @return string cu insert
     */
    private String creareInsert(String nume, String valori)
    {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tema3tp.");
        sb.append(tip.getSimpleName());
        sb.append(" (" + nume+ ") ");
        sb.append("VALUES (" + valori + ");");
        return sb.toString();
    }
    /**
     * Metoda de creare a lui update
     * @param s
     * @return striing cu update
     */
    private String creareUpdate(String s)
    {

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tip.getSimpleName());
        sb.append(" SET " + s);
        sb.append(" WHERE id = ?;");
        return sb.toString();
    }
    /**
     * Metoda de creare a lui delete
     * @return string cu delete
     */
    private String creareDelete()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(tip.getSimpleName());
        sb.append(" WHERE id = ?;");
        return sb.toString();
    }
    /**
     * Metoda de adaugarea
     * @param nume-pt query-ul de inserare
     * @param valori -pt query-ul de inserare
     * @return id
     */
    public int Adaug(String nume, String valori)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        String query = creareInsert(nume, valori);
        PreparedStatement insertStatement = null;
        int idpus = -1;
        try {
            insertStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next())
            {
                idpus = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:adaug " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return idpus;
    }
    /**
     * Metoda de update
     * @param nume-pentru query-ul de update
     * @param id-se face update dupa id
     * @return valoarea 0
     */
    public int Update(String nume, int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        String query = creareUpdate(nume);
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(query);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return 0;
    }
    /**
     * Metoda de stergere
     * @param id-se face stergerea dupa id
     * @return valoarea 0
     */
    public int Sterg(int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        String query = creareDelete();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(query);
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:sterg " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return 0;
    }
    /**
     * Metoda de gasire dupa id
     * @param id-gasire dupa id
     * @return null
     */
    public T GasireId(int id)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = creareSelect("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return creareobj(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, tip.getName() + "DAO:gasiredupaid " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public ArrayList<T> Gasire()
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = creareSelectTot();
        try {
            findStatement = dbConnection.prepareStatement(query);
            rs = findStatement.executeQuery();
            return (ArrayList<T>) creareobj(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"DAO:gasiredupaid " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
    /**
     * Metoda de creare a obiectelor
     * @param resultSet
     * @return o lista
     */
    private List<T> creareobj(ResultSet resultSet)
    {
        List<T> lista = new ArrayList<T>();
        Constructor[] c1 = tip.getDeclaredConstructors();
        Constructor c2 = null;
        for (int i = 0; i < c1.length; i++)
        {
            c2 = c1[i];
            if (c2.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next())
            {
                c2.setAccessible(true);
                T instance;
                instance = (T) c2.newInstance();
                for (Field field : tip.getDeclaredFields())
                {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, tip);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                lista.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
