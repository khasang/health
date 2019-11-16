package org.health.dao;


import java.util.List;

public interface BasicDao<T extends IGettingID> {
    /**
     * method required for adding entity
     *
     * @param entity - entity for adding
     * @return created entity
     */
    T addEntity(T entity);

    /**
     * method required for updating entity
     *
     * @param entity - entity for update
     * @return updated entity
     */
    T updateEntity(T entity);

    /**
     * method required for getting entity by id
     *
     * @param id - id entity got getting
     * @return entity by id
     */
    T getEntity(long id);

    /**
     * method required for getting all entities
     *
     * @return all entities
     */
    List<T> getAllEntities();

    /**
     * method required for deletion entity by id
     *
     * @param entity - entity for delete
     * @return deleted entity by id
     */
    T deleteEntity(T entity);
}
