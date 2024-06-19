package com.unla.grupo8_oo2.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.UserRole;
import com.unla.grupo8_oo2.repositories.IUserRepository;



@Service("userService")
public class UserService implements UserDetailsService {

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<com.unla.grupo8_oo2.entities.User> findUsers() {
		List<com.unla.grupo8_oo2.entities.User> users = new ArrayList<com.unla.grupo8_oo2.entities.User>();
		users = (List<com.unla.grupo8_oo2.entities.User>) userRepository.findUsers();
		return users;
	}

	public List<com.unla.grupo8_oo2.entities.User> findAdmins() {
		List<com.unla.grupo8_oo2.entities.User> admins = new ArrayList<com.unla.grupo8_oo2.entities.User>();
		admins = (List<com.unla.grupo8_oo2.entities.User>) userRepository.findAdmins();
		return admins;
	}

	public com.unla.grupo8_oo2.entities.User findUserByUsername(String username) {
		com.unla.grupo8_oo2.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.grupo8_oo2.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}

	private User buildUser(com.unla.grupo8_oo2.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (UserRole userRole : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
}
	
	
	

