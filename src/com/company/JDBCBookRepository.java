package com.company;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBookRepository  implements  BookRepository{

    private final DataSource dataSource;

    public JDBCBookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void create(Book book) {
        Statement statement=null;
        Connection connection=null;

        try {
              connection = dataSource.getConnection();

             statement = connection.createStatement();
            statement.execute("insert into books (title, author) values ('" +
                    book.getTitle() + "', '" + book.getAuthor() + "');");
            System.out.println(connection);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
