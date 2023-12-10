package com.example.Analytics.dto;

import lombok.Data;

@Data
public class LogsDTO {
    private String userId;
    private String eventType;
    private String serviceApp;
    private String category;
    private String pid;
}
