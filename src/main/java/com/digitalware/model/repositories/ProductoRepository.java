
package com.digitalware.model.repositories;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.digitalware.model.entities.ProductoEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductoRepository implements CrudRepository<Long, ProductoEntity> {
    @Autowired
    private SequenceRepository sequenceRepository;

    @Override
    public ProductoEntity add(ProductoEntity entity) throws Exception {
        Map<Long, ProductoEntity> productos = this.findAll();

        if (entity.getId() == null)
            entity.setId(sequenceRepository.nextProductId());

        productos.put(entity.getId(), entity);

        new ObjectMapper().writeValue(
                ResourceUtils.getFile(
                        new URL("file:src/main/resources/persistence/productos.json").toURI()),
                productos);

        return entity;
    }

    @Override
    public Map<Long, ProductoEntity> findAll() {
        Map<Long, ProductoEntity> productos = null;

        try {
            productos = new ObjectMapper().readValue(
                    new URL("file:src/main/resources/persistence/productos.json"),
                    new TypeReference<Map<Long, ProductoEntity>>() {
                    });
        } catch (IOException e) {
            productos = new HashMap<>();

            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public ProductoEntity findById(Long id) {
        return this.findAll().get(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Map<Long, ProductoEntity> productos = this.findAll();

        productos.remove(id);

        new ObjectMapper().writeValue(
                ResourceUtils.getFile(
                        new URL("file:src/main/resources/persistence/productos.json").toURI()),
                productos);
    }

}
