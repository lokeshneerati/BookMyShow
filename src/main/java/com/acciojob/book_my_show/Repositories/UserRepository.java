package com.acciojob.book_my_show.Repositories;

import com.acciojob.book_my_show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
