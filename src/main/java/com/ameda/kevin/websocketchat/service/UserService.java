package com.ameda.kevin.websocketchat.service;

import com.ameda.kevin.websocketchat.entity.Status;
import com.ameda.kevin.websocketchat.entity.User;
import com.ameda.kevin.websocketchat.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }
    public void disconnect(User user){
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if(storedUser!=null){
            storedUser.setStatus(Status.OFFLINE);
            //updating status of stored user.
            repository.save(user);
        }
    }

    public List<User> findConnectedUsers(){
        return repository.findAllByStatus(Status.ONLINE);
    }
}
