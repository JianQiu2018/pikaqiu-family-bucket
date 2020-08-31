package com.pikaqiu.familybucket.security;

import com.pikaqiu.familybucket.entities.AuthUser;
import com.pikaqiu.familybucket.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 22:28
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AuthUser> userOptional = authUserRepository.findByPhoneNumber(s);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
//        System.out.println("username:"+userOptional.get().getUsername()+";password:"+userOptional.get().getPassword());
//        List<GrantedAuthority> authList = new ArrayList<>();
//        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//        UserDetails userDetail = new User(userOptional.get().getUsername(), userOptional.get().getPassword(), authList);
        return null;
    }



}
