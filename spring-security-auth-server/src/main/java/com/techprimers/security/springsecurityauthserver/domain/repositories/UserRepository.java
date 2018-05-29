package com.techprimers.security.springsecurityauthserver.domain.repositories;

import com.techprimers.security.springsecurityauthserver.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Rith on 5/29/2018.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
