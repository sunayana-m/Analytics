package com.example.Analytics.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Document(collection = "logs")
@Data
public class Logs {

    @Id
    @MongoId
    private String id;

    private String userId;
    private String eventType;    //like,comment,login,downvote,upvote,logout,follow,unfollow,addFriend,removeFriend,imgPost,vidPost

    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime timestamp;

    private String serviceApp;
    private String category;
    private String pid;
}
