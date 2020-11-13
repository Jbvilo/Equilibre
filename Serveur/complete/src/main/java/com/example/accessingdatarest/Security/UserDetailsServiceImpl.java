package com.example.accessingdatarest.Security;

import java.util.Collection;
import java.util.List;

import com.example.accessingdatarest.Databases.PersonRepository;
import com.example.accessingdatarest.Models.Person;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PersonRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailsServiceImpl(PersonRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Person applicationUser
       List<Person> list = applicationUserRepository.findByEmail(email);
        Person applicationUser =list.get(0);

     
            if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
            }
       

 
        return new User(applicationUser.getFirstName(),applicationUser.getPassword(),emptyList() );
    }


  
}