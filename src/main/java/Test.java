import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Book book = new Book(1, "ABCD","XYZ",1994, 12345676);
        BookDao dao = new BookDao();
        dao.save(book);

    }
}
