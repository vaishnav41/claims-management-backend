package com.cognizant.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> listOfUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public UserDTO authenticateUser(String username, String password) {
        List<Users> users = listOfUsers();
        UserDTO userDTO = new UserDTO();
        for(Users user: users){
            if(user.getUserName().equals(username) && user.getPassword().equals(password) && !user.isAccountLocked()){
                userDTO.setUserName(username);
                userDTO.setPassword(password);
                userDTO.setRole(user.getRole());
                userDTO.setAccountLocked(user.isAccountLocked());
                break;
            }
        }
        return userDTO;
    }
}
