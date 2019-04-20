package com.example.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAll(Pageable pageable);

    List<Client> findByName(String name);

    List<Client> findByGenderAndAgeLessThanEqual(String gender, int age);

    @Query(value = "select count(*) from client where gender = ?1 ", nativeQuery = true)
    int findCountByGender(String gender);

    @Query("select c from Client c where c.email like %?1%")
    List<Client> findByEmailKeyWord(String keyWord);

    @Query("select c from Client c where c.gender = ?1")
    List<Client> findByGender(String gender);

    @Modifying
    @Query("update Client c set c.password = ?1 where c.gender = ?2")
    int updatePwdByGender(String password, String gender);

    @Modifying
    @Query("delete from Client c where c.id = ?1")
    int deleteById(long id);


}
