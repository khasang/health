package org.health.service;

import org.health.entity.Horse;

import java.util.List;

public interface HorseService {
    /**
     * method required for adding horse
     *
     * @param horse - horse for adding
     * @return created horse
     */
    Horse addHorse(Horse horse);

    /**
     * method required for updating horse
     *
     * @param horse - horse for update
     * @return updated horse
     */
    Horse updateHorse(Horse horse);

    /**
     * method required for getting horse by id
     *
     * @param id - id horse got getting
     * @return horse by id
     */
    Horse getHorse(long id);

    /**
     * method required for getting all horses
     *
     * @return all horses
     */
    List<Horse> getAllHorses();

    /**
     * method required for deletion horse by id
     *
     * @param id - id horse got delete
     * @return deleted horse by id
     */
    Horse deleteHorse(long id);
}
