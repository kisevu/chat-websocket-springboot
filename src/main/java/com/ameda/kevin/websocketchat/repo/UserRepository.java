package com.ameda.kevin.websocketchat.repo;

import com.ameda.kevin.websocketchat.entity.Status;
import com.ameda.kevin.websocketchat.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
}
