package com.company;

public class ProxyBook extends Book {

    private Book book;
    private boolean flag = false;

    @Override
    public String getTitle() {
        if (flag) return super.getTitle();
        throw new NotLoadedException();
    }

    @Override
    public String getAuthor() {
        if (flag) return super.getAuthor();
        throw new NotLoadedException();
    }

    public boolean isLoaded() {
        return flag;
    }

    public void Load() {
        if (flag) throw new OverLoadedException();
        flag = true;
    }
}