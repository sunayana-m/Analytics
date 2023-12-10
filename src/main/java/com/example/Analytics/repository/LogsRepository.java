package com.example.Analytics.repository;

import com.example.Analytics.entity.Logs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends MongoRepository<Logs,String> {

    @Query("{'eventType': ?0}")
    List<Logs> findDistinctUsersByEventType(String eventType);

    @Query("{'eventType': { $ne: null }, 'serviceApp': ?0  }")
    List<Logs> findLogsByEventTypeForServiceApp(String serviceApp);

    @Query("{'category': { $ne: null }, 'serviceApp': ?0  }")
    List<Logs> findLogsByCategoryForServiceApp(String serviceApp);


    @Query("{'eventType': ?0, 'category': { $ne: null } }")
    List<Logs> findLogsByEventTypeForCategory(String eventType);


    @Query("{'eventType': 'like', 'category': { $ne: null } }")
    List<Logs> findLikesWithCategories();

}
