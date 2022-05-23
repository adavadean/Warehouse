import presentation.ClientView;
import presentation.Controller;
import presentation.OrderView;
import presentation.ProdusView;

public class  Start {
    public static void main(String[] args)
    {
        ClientView c = new ClientView();
        ProdusView p = new ProdusView();
        OrderView o = new OrderView();
        Controller controller = new Controller(c, p, o);
    }
}
