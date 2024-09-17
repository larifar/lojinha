package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where upper(trim(u.name)) like %?1%")
    List<User> findByName(String name);
    @Query(value = "select u from User u where u.email =?1")
    User findUserByEmail(String email);

    @Query(value = "select u from User u where u.CPF =?1")
    User findUserByCPF(String cpf);

    @Query(value = " SELECT k.CONSTRAINT_NAME\n" +
            "FROM information_schema.KEY_COLUMN_USAGE k\n" +
            "         JOIN information_schema.TABLE_CONSTRAINTS c\n" +
            "              ON k.CONSTRAINT_NAME = c.CONSTRAINT_NAME\n" +
            "                  AND k.TABLE_NAME = c.TABLE_NAME\n" +
            "WHERE k.TABLE_NAME = 'users_access'\n" +
            "  AND k.COLUMN_NAME = 'access_id'\n" +
            "  AND c.CONSTRAINT_TYPE = 'UNIQUE'\n" +
            "  AND k.CONSTRAINT_NAME != 'unique_user_access';", nativeQuery = true)
    String findUserConstraint();

    @Transactional
    @Modifying
    @Query(value = "insert into users_access(user_id, access_id) values (?1, (select id from access where description = 'ROLE_USER'))", nativeQuery = true)
    void insertUserAccess(Long id);

    @Transactional
    @Modifying
    @Query(value = "insert into users_access(user_id, access_id) values (?1, (select id from access where description = ?2))", nativeQuery = true)
    void insertUserAccess(Long id, String access);
}
