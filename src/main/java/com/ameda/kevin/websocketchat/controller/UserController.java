package com.ameda.kevin.websocketchat.controller;

import com.ameda.kevin.websocketchat.entity.User;
import com.ameda.kevin.websocketchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @MessageMapping("/user.addUser") // add new user
    @SendTo("/user/topic")
    //inform all connected users of new user joining and display in our connected users
    //sending to a specific topic and the queue here is automatically created.
    public User addUser(@Payload User user){
        userService.saveUser(user);
        //returning user is essential as we need subscribing to "/user/topic" queue
        //Also for notification purposes...
        return user;
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<?> findConnectedUsers(){
        return ResponseEntity.ok(userService.findConnectedUsers());
    }

}
