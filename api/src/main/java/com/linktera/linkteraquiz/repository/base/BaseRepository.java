package com.linktera.linkteraquiz.repository.base;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T> {

    List<T> getList();

    T get(UUID uuid);

    void save(T entity);

    void update(UUID uuid, T entity);

    void delete(UUID uuid);

}
