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

    //registered users for param service app
    @Query("{'eventType': 'login', 'serviceApp': ?0}")
    List<Logs> findDistinctUsersForServiceApp(String serviceApp);


    //count of event type
    @Query("{'eventType': { $ne: null }, 'serviceApp': ?0  }")
    List<Logs> findLogsByEventTypeForServiceApp(String serviceApp);

    @Query("{'category': { $ne: null }, 'serviceApp': ?0  }")
    List<Logs> findLogsByCategoryForServiceApp(String serviceApp);


    @Query("{'eventType': ?0, 'category': { $ne: null } }")
    List<Logs> findLogsByEventTypeForCategory(String eventType);


    @Query("{'eventType': 'like', 'category': { $ne: null } }")
    List<Logs> findLikesWithCategories();


    //distinct users for each service app
    @Query(value = "{'eventType': 'login'}", fields = "{ 'username': 1, 'serviceApp': 1 }")
    List<Logs> findDistinctUsersForEachServiceApp();

}
