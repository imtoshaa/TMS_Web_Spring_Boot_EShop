package by.teachmeskills.eshop.dao;

import by.teachmeskills.eshop.domain.entities.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {

    void create(T entity) throws Exception;

    List<T> read() throws Exception;

}
