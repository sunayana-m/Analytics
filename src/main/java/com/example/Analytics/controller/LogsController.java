package com.example.Analytics.controller;

import com.example.Analytics.dto.LogsDTO;
import com.example.Analytics.entity.Logs;
import com.example.Analytics.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/logging")
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/get-all")
    public List<Logs> getAllLogs(){
        return logsService.getAllLogs();
    }

    @PostMapping("/add")
    public boolean addLog(@RequestBody LogsDTO logsDTO){

        boolean response = logsService.addLogs(logsDTO);
        return response;
    }

    @GetMapping("/registeredUsers")
    public long countRegisteredUsers(){
        List<Logs> listOfUsers =  logsService.findDistinctUsersByEventType("login");
        return listOfUsers.size();
    }

    @GetMapping("/distinctUsersForEachServiceApp")
    public Map<String, Long> getDistinctUsersForEachServiceApp() {
        return logsService.findDistinctUsersForEachServiceApp();
    }

    @GetMapping("/registeredUsers/{serviceApp}")
    public long getDistinctUsersForServiceApp(@PathVariable String serviceApp) {
        List<Logs> listOfUsers = logsService.findDistinctUsersForServiceApp(serviceApp);
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