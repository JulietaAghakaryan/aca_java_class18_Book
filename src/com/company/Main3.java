package com.company;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        HikariDataSource dataSource = new HikariDataSource();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        BookRepository bookRepository = new JDBCBookRepository(dataSource);
        Book book = new Book();


        dataSource.setJdbcUrl( "jdbc:postgresql://localhost:5432/aca" );
        dataSource.setUsername( "postgres" );
        dataSource.setPassword( "postgres" );

        final long start = System.currentTimeMillis();
        List<Future<?>> list = new LinkedList<>();
        for (int i = 0; i < 10_000; i++) {
            int finalI = i;
            Future<?> future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    final int a = finalI;
                    book.setTitle("Java");
                    book.setAuthor(String.format("Author %d", a));
                    bookRepository.create(book);
                }
            });
            list.add(future);
        }
        executorService.shutdown();

        for(final Future<?> future: list){
            future.get();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}

