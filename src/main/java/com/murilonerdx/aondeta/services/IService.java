package com.murilonerdx.aondeta.services;

import java.util.List;

public interface IService<O, I> {
    O create(O o );
    O update(O o, I i) throws Exception;
    void deleteById(I i);
    List<O> listAll();
    O findById(I i);
}
