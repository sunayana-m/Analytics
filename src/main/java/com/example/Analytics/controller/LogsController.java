package com.example.Analytics.controller;

import com.example.Analytics.dto.LogsDTO;
import com.example.Analytics.entity.Logs;
import com.example.Analytics.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logging")
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/get-all")
    private List<Logs> getAllLogs(){
        return logsService.getAllLogs();
    }

    @PostMapping("/add")
    private boolean addLog(@RequestBody LogsDTO logsDTO){

        boolean response = logsService.addLogs(logsDTO);
        return response;
    }

    @GetMapping("/registeredUsers")
    private long countRegisteredUsers(){
        List<Logs> listOfUsers =  logsService.findDistinctUsersByEventType("login");
        return listOfUsers.size();
    }

    @GetMapping("/eventCountForCategories/{eventType}")
    public Map<String, Long> getEventCountForCategories(@PathVariable String eventType) {
        return logsService.findEventCountForCategories(eventType);
    }

    @GetMapping("/eventCountByServiceApp/{serviceApp}")
    public Map<String, Long> getEventCountByServiceApp(@PathVariable String serviceApp) {
        return logsService.findEventCountByServiceApp(serviceApp);
    }

    @GetMapping("/categoryCountByServiceApp/{serviceApp}")
    public Map<String, Long> getCategoryCountByServiceApp(@PathVariable String serviceApp) {
        return logsService.findCategoryCountByServiceApp(serviceApp);
    }



}
