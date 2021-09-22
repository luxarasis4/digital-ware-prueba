
package com.digitalware.model.repositories;

import java.util.Map;

public interface CrudRepository<ID, T> {
    public T add(T Entity) throws Exception;

    public Map<ID, T> findAll();

    public T findById(ID id);

    public void delete(ID id) throws Exception;
}
