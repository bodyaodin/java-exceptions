package com.projects.managers;

import com.projects.exceptions.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class DataManager implements Manager {

    private Map<Long, String> URLs;
    private Long idURL;

    public DataManager(Map<Long, String> URLs) {
        this.URLs = URLs;
        this.idURL = 0L;
    }

    @Override
    public Long addURL(String URL) throws DataException {
        URLs.put(++idURL, URL);
        return idURL;
    }

    @Override
    public String deleteURL(Long id) throws DataException {
        return Optional.of(URLs.remove(id))
                .orElseThrow(() -> {throw new ValueNotFoundException("There is no such URL in Map!");});
    }

    @Override
    public Map<Long, String> getAll() throws MapIsEmptyException {
        Optional.of(URLs)
                .filter(Predicate.not(Map::isEmpty))
                .orElseThrow(() -> {throw new MapIsEmptyException("Map does not contain any value!");});
        return URLs;
    }
}
