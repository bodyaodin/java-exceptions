package com.projects.managers;

import java.util.Map;

public interface Manager {

    Long addURL(String url);
    String deleteURL(Long id);
    Map<Long, String> getAll ();

}
