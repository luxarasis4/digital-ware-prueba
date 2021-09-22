
package com.digitalware.model.repositories;

import java.util.Map;

public interface CrudRepository<ID, T> {
    public T add(T entity) throws Exception;

    public Map<ID, T> findAll() throws Exception;

    public T findById(ID id) throws Exception;

    public void delete(ID id) throws Exception;
}
