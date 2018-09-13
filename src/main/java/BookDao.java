import java.sql.*;

public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/books";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;

    public BookDao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void save(Book book) throws SQLException {
        final String sql = "insert into book(id, title, author,year, isbn) values(?, ?, ?,?,?)";
        PreparedStatement prepaState = connection.prepareStatement(sql);
        prepaState.setInt(1, book.getID());
        prepaState.setString(2, book.getTitle());
        prepaState.setString(3, book.getAuthor());
        prepaState.setInt(4, book.getYear());
        prepaState.setInt(5, book.getIsbn());
        prepaState.executeUpdate();
    }

    public Book read(int id) throws SQLException {
        final String sql = "select id,title,author,year,isbn from books where id = ?";
        PreparedStatement prepState = connection.prepareStatement(sql);
        prepState.setInt(1, id);
        ResultSet resultSet = prepState.executeQuery();
        if (resultSet.next()) {
            Book book = new Book();
            book.setID(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setYear(resultSet.getInt("year"));
            book.setIsbn(resultSet.getInt("isbn"));
            return book;
        }
        return null;
    }

    public void update(Book book) throws SQLException {
        final String sql = "update books set id=?, title=?, author=?,year=?,isbn? where id=?";
        PreparedStatement prepState = connection.prepareStatement(sql);
        prepState.setInt(1,book.getID());
        prepState.setString(2,book.getTitle());
        prepState.setString(3,book.getAuthor());
        prepState.setInt(4,book.getYear());
        prepState.setInt(5,book.getIsbn());
        prepState.executeUpdate();
        }

    public void delete(int id) throws SQLException {
        final String sql = "delete from books where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
}



