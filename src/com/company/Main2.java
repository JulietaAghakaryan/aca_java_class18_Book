package com.company;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {


        System.out.println("which datasource do you want ");
        System.out.println("0:hikari");
        System.out.println("1:postgre");
        Scanner s= new Scanner(System.in);
        int a = s.nextInt();




            BookRepository bookRepository = new JDBCBookRepository(resolveDataSource(a));
            Book book = new Book();

            final long start = System.currentTimeMillis();
            for (int i = 0; i < 1000 ; i++) {
                book.setTitle("Java");
                book.setAuthor(String.format("Author %d", i));//14000 darcav 3228
                bookRepository.create(book);
            }
            System.out.println(System.currentTimeMillis()-start);
        }

    public static DataSource getHikariDatasource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/aca" );
        config.setUsername( "postgres" );
        config.setPassword( "postgres" );
//      config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        return new HikariDataSource(config);
    }

    public static DataSource getPgDataSourcce() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/aca");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }


    public static DataSource resolveDataSource(int selectedId) {
        if (selectedId == 0) {
            return getHikariDatasource();
        }if(selectedId==1){
            return  getPgDataSourcce();
    }
        throw new IllegalArgumentException();
}
}
