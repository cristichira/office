package com.app.office.user.impl.service;

import com.app.office.user.api.UserService;
import com.app.office.user.api.dto.CurrentUser;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.domain.User;
import com.app.office.user.impl.repository.UserRepository;
import com.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityService securityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           SecurityService securityService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public UserDTO getCurrentUser() {

        long currentUserId = securityService.getCurrentUser().getUserId();
        return findById(currentUserId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

//            if (user.get().isLocked()) {
//                throw new LockedException(username);
//            }

            final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.toString())));

            return new CurrentUser(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with email: " + email));
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
