package com.person.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "student_cache")
@Data
public class StudentCache implements Serializable {
    private String id;
    private String name;
}
