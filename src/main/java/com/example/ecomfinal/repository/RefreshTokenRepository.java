package com.example.ecomfinal.repository;

import com.example.ecomfinal.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {


    @Query(value = "SELECT * FROM refresh_token WHERE token = ?1 ",nativeQuery = true)
    Optional<RefreshToken> findByToken(String refreshtoken);

    @Modifying
    @Transactional
    @Query(value = "DELETE  from refresh_token WHERE token = ?1 ", nativeQuery = true)
    int deleteByToken(String refreshtoken);
}
