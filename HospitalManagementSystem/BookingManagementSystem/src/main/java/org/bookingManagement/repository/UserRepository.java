package org.bookingManagement.repository;

import lombok.Data;
import org.bookingManagement.exceptions.UserAlreadyPresentException;
import org.bookingManagement.model.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserRepository {

    private static final Map<String, User> registeredUsers = new HashMap<>();

    public void registerUser(User user){
        if(registeredUsers.containsKey(user.getName()))
            throw new UserAlreadyPresentException("The user already exists in the system!!");
        registeredUsers.put(user.getName(), user);
    }

    public Boolean isRegistered(String userName){
        return registeredUsers.containsKey(userName);
    }
}
