package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.UserDAO;
import com.expensetracker.expense_tracker.entity.Role;
import com.expensetracker.expense_tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public User findUserById(int id) {
        Optional<User> user = userDAO.findById(id);

        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new RuntimeException("Did not find the id: " + id);
        }
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userDAO.findById(id);

        if (user.isPresent()) {
            userDAO.deleteById(id);
        }
        else {
            throw new RuntimeException("Did not find the id: " + id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> rolse) {
        return rolse.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

}
