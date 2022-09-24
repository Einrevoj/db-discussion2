package jcano.bediscussion2.repository;

import jcano.bediscussion2.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.math.BigInteger;
import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, BigInteger> {
    List<UsersEntity> findAllBy();
    UsersEntity findByEmail(String email);
}
