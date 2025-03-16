package com.person.service;

import com.person.entity.StudentCache;
import com.person.repository.StudentCachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    //first method
//    private final RedisTemplate<String,Object> redisTemplate;
//
//
//    public void save(){
//        redisTemplate.opsForValue().set("studentKey","studentValue");
//    }

    //second method
    private final StudentCachRepository cachRepository;

    public void save(StudentCache student){
        cachRepository.save(student);
    }

}

