package com.xzixi.demo.model;

import java.util.List;

public interface Tree<T extends Tree> {

    Integer getId();

    Integer getPid();

    Integer getLevel();

    List<T> getSubs();

    T setSubs(List<T> subs);

}
