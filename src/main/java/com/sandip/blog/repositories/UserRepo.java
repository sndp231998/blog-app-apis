package com.sandip.blog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sandip.blog.entities.User;
public interface UserRepo extends JpaRepository<User,Integer>{

}
