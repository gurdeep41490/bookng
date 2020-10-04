package com.org.jwt.service;


import com.org.jwt.model.User;
import com.org.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User registerUser(User user){
        User user1 = userRepo.getOne(user.getMobileNo());
        if(null != user1) return user1;
        else{
            User userNew = userRepo.save(user);
            return userNew;
        }
    }

    public User findUserByName(String username) {
        return userRepo.findByName(username);
    }
}
