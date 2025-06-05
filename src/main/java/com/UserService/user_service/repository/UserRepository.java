package com.UserService.user_service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.UserService.user_service.model.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public int registerUser(User user) {
        //String sql = "INSERT INTO userpaychat (username, \"password\", email, \"role\") VALUES (?, ?, ?, ?)";
        String sql = "INSERT INTO userss (username,\"password\",email, \"role\")VALUES(?, ?, ?, ?)";

        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
    }

	
    private static final String FIND_USER_BY_USERNAME_QUERY =
    	    "SELECT * FROM userss WHERE username = ?";

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(
            FIND_USER_BY_USERNAME_QUERY,
            (rs, rowNum) -> {
                User user = new User();
               // user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                //user.setRole(rs.getString("role"));
                return user;
            },
            username // now passed as varargs
        );
    }
}

