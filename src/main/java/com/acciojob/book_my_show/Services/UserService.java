package com.acciojob.book_my_show.Services;

import com.acciojob.book_my_show.Models.User;
import com.acciojob.book_my_show.Repositories.UserRepository;
import com.acciojob.book_my_show.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String addUser(AddUserRequest userRequest) {

        User user = User.builder().age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .name(userRequest.getName())
                .mobileNo(userRequest.getMobileNo())
                .build();

        user = userRepository.save(user);
        return "User has been saved to the DB with userId "+user.getUserId();

    }



}
