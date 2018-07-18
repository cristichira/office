package com.app.office.user.impl.service;

import com.app.office.user.api.UserService;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.domain.User;
import com.app.office.user.impl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with username: " + username));
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with id: " + id));
    }

    @Override
    public List<UserDTO> find() {
        Specification<User> query = UserSpecification.unrestricted();
        return userRepository.findAll(query).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long save(UserDTO userDTO) {
        User user = userDTO.getId() != null ? userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with id: " + userDTO.getId()))
                : new User();
        return userRepository.save(userMapper.toEntity(userDTO, user)).getId();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
