package com.example.Analytics.service.impl;

import com.example.Analytics.dto.LogsDTO;
import com.example.Analytics.entity.Logs;
import com.example.Analytics.repository.LogsRepository;
import com.example.Analytics.service.LogsService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    LogsRepository logsRepository;

    @Override
    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    @Override
    public boolean addLogs(LogsDTO logsDTO){
        Logs logs = new Logs();
        BeanUtils.copyProperties(logsDTO,logs);
        logs.setId(UUID.randomUUID().toString());
        logs.setTimestamp(LocalDateTime.now());
        Logs savedLog = logsRepository.save(logs);
        return Objects.nonNull(savedLog);
    }

    @Override   //global - to find distinct users registered , evevntType=login
    public List<Logs> findDistinctUsersByEventType(String eventType) {
        return logsRepository.findDistinctUsersByEventType(eventType);
    }

    @Override   //find no of distinct users registered on a specified service app
    public List<Logs> findDistinctUsersForServiceApp(String serviceApp) {
        return logsRepository.findDistinctUsersForServiceApp(serviceApp);
    }

    @Override   //global - find number of distinct users for each service app
    public Map<String, Long> findDistinctUsersForEachServiceApp() {
        List<Logs> usersLogs = logsRepository.findDistinctUsersForEachServiceApp();

        // Count occurrences of each category for the specified eventType
        Map<String, Long> usersCounts = usersLogs.stream()
                .collect(Collectors.groupingBy(Logs::getServiceApp, Collectors.counting()));

        // Sort the categories by count in descending order
        return usersCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public Map<String, Long> findEventCountForCategories(String eventType) {
        List<Logs> eventLogs = logsRepository.findLogsByEventTypeForCategory(eventType);

        // Count occurrences of each category for the specified eventType
        Map<String, Long> categoryCounts = eventLogs.stream()
                .collect(Collectors.groupingBy(Logs::getCategory, Collectors.counting()));

        // Sort the categories by count in descending order
        return categoryCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    @Override
    public Map<String, Long> findPopularCategoriesForLikes() {
        List<Logs> likeEventsWithCategories = logsRepository.findLikesWithCategories();

        Map<String, Long> categoryCounts = likeEventsWithCategories.stream()
                .collect(Collectors.groupingBy(Logs::getCategory, Collectors.counting()));

        // Sort the categories by count in descending order
        return categoryCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public Map<String, Long> findEventCountByServiceApp(String serviceApp) {
        List<Logs> logs = logsRepository.findLogsByEventTypeForServiceApp(serviceApp);

        // Count occurrences of each event type
        Map<String, Long> eventCounts = logs.stream()
                .collect(Collectors.groupingBy(Logs::getEventType, Collectors.counting()));

        // Sort the categories by count in descending order
        return eventCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public Map<String, Long> findCategoryCountByServiceApp(String serviceApp) {
        List<Logs> logs = logsRepository.findLogsByCategoryForServiceApp(serviceApp);

        // Count occurrences of each category
        Map<String, Long> categoryCounts = logs.stream()
                .collect(Collectors.groupingBy(Logs::getCategory, Collectors.counting()));

        // Sort the categories by count in descending order
        return categoryCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


}
