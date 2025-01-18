package com.mihir.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihir.SpringSecurity.Model.Sessions;

@Repository
public interface SessionRepo extends JpaRepository<Sessions, Integer> {

    int countByUserId(int userId);

    int deleteByRefreshToken(String refreshToken);
}
