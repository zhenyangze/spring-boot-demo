package com.example.model;

import java.util.List;

public interface Tree<T> {

    Integer getId();

    Integer getPid();

    Integer getLevel();

    List<T> getSubs();

    void setSubs(List<T> subs);

}
