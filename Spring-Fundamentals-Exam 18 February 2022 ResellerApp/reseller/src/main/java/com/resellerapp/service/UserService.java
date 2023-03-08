package com.resellerapp.service;

import com.resellerapp.model.dto.LoginUserDTO;
import com.resellerapp.model.dto.RegisterUserDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void register(RegisterUserDTO registerUserDTO) {
        User user = this.modelMapper.map(registerUserDTO, User.class);
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        this.userRepository.save(user);
    }

    public boolean login(LoginUserDTO loginUserDTO) {
        User user = this.findByUsername(loginUserDTO.getUsername());

        if (user == null){
            return false;
        }

        if (!this.passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())){
            return false;
        }

        this.loggedUser.login(user);

        return true;
    }

    public void logOut() {
        this.loggedUser.clearValues();
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
