package com.company;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCBookRepositoryOld implements  BookRepository{

        private final DataSource dataSource;

        public JDBCBookRepositoryOld(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Override
        public void create(Book book) {
            Statement statement=null;
            Connection connection=null;

            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/aca",
                        "postgres",
                        "postgres");//db kpnelu 1 qayl

                statement = connection.createStatement();
                statement.execute("insert into books (title, author) values ('" +
                        book.getTitle() + "', '" + book.getAuthor() + "');");


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


