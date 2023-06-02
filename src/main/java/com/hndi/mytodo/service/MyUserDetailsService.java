package com.hndi.mytodo.service;

import com.hndi.mytodo.model.AuthenticationRequest;
import com.hndi.mytodo.model.User;
import com.hndi.mytodo.repository.UserRepository;
import com.hndi.mytodo.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> user=userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException ("Not Found: " +username));
        return user.map(MyUserDetails::new).get();
    }

    public void createUser(AuthenticationRequest authenticationRequest) {
        // TODO Auto-generated method stub
        User user= new User();
        user.setUserName(authenticationRequest.getUsername());
        user.setPassword(authenticationRequest.getPassword());
        user.setActive(true);
        user.setRoles("GUEST");
        userRepository.save(user);
    }

    public User getUser(String username, String authString) throws AccessDeniedException {
        // TODO Auto-generated method stub

        String jwt=null;
        String usernameJwt=null;
        if(authString != null && authString.startsWith("Bearer ")) {
            jwt= authString.substring(7);
            usernameJwt=jwtUtil.extractUsername(jwt);
        }

        if (username.equals(usernameJwt))
            return userRepository.findByUserName(username).get();
        else
            throw new AccessDeniedException("Not Authorized to view other users info");
    }

    public void updateUser(String username, User user) {
        User userDB =userRepository.findByUserName(username).get();

        if (!userDB.getUserName().equalsIgnoreCase(user.getUserName()))
            throw new IllegalArgumentException("Not Allowed to update username");

        if (!userDB.getPassword().equals(user.getPassword()))
            userDB.setPassword(user.getPassword());

        if (userDB.isActive()!=user.isActive())
            userDB.setActive(user.isActive());

        if (Objects.nonNull(user.getRoles()) &&
                !userDB.getRoles().equals(user.getRoles()))
            userDB.setRoles(user.getRoles());

        userRepository.save(userDB);
    }

}
