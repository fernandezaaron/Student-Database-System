import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException
    {
        database db = database.getInstance();
        db.initialize();
        Login log = new Login();
    }
}