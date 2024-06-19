package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.User;


@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles ur WHERE ur.role = ('ROLE_USER')")
	public abstract List<User> findUsers();

	@Query("SELECT u FROM User u JOIN FETCH u.userRoles ur WHERE ur.role = ('ROLE_ADMIN')")
	public abstract List<User> findAdmins();

	public abstract User findByUsername(String username);


}
