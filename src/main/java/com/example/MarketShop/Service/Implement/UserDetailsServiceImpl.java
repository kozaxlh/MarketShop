package com.example.MarketShop.Service.Implement;

import com.example.MarketShop.Model.Roles;
import com.example.MarketShop.Model.Users;
import com.example.MarketShop.Repository.RolesRepository;
import com.example.MarketShop.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByEmail(email);

        if(users.isEmpty())
            throw new UsernameNotFoundException("User is not found");

        List<String> rolesName = rolesRepository.findAll()
                .stream()
                .map(Roles::getRoleName)
                .collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorities = new ArrayList();
        if(rolesName != null) {
            for(String role : rolesName) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(authority);
            }
        }

        UserDetails userDetails = new User(users.get().getEmail(), users.get().getPassword(), grantedAuthorities);

        return userDetails;
    }
}
