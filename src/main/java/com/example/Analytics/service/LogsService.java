package com.example.Analytics.service;

import com.example.Analytics.dto.LogsDTO;
import com.example.Analytics.entity.Logs;

import java.util.List;
import java.util.Map;

public interface LogsService {
    List<Logs> getAllLogs();

    boolean addLogs(LogsDTO logsDTO);


    List<Logs> findDistinctUsersByEventType(String eventType);


    List<Logs> findDistinctUsersForServiceApp(String serviceApp);

    //global - find number of distinct users for each service app
    Map<String, Long> findDistinctUsersForEachServiceApp();

    Map<String, Long> findEventCountForCategories(String eventType);

    Map<String, Long> findPopularCategoriesForLikes();

    Map<String, Long> findEventCountByServiceApp(String serviceApp);

    Map<String, Long> findCategoryCountByServiceApp(String serviceApp);
}
