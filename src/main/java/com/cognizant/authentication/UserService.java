package com.cognizant.authentication;

import java.util.List;

public interface UserService {
    public List<Users> listOfUsers();

    public UserDTO authenticateUser(String username, String password);
}