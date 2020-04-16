package com.nfinity.demo.petsdemos.domain.irepository; 
 
import com.nfinity.demo.petsdemos.domain.model.JwtEntity; 
import org.springframework.data.jpa.repository.JpaRepository; 
 
import java.util.List; 

public interface IJwtRepository extends JpaRepository<JwtEntity, Long> { 

    JwtEntity findByToken(String token); 
    
} 