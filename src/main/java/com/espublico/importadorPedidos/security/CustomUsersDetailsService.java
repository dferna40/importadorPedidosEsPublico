package com.espublico.importadorPedidos.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.model.Roles;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.UserRepository;


@Service
public class CustomUsersDetailsService implements UserDetailsService {

	private UserRepository userRepo;
	
	@Autowired
	public CustomUsersDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    for (Roles role : roles) {
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	    }
	    return authorities;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepo.findByUserName(username).orElseThrow(new Supplier<UsernameNotFoundException>() {
	        @Override
	        public UsernameNotFoundException get() {
	            return new UsernameNotFoundException("Usuario no encontrado");
	        }
	    });
	    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapToAuthorities(user.getRoles()));
	}
	
	

}
