package unisinos.tripverse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisinos.tripverse.configuration.UserProvider;
import unisinos.tripverse.model.user.*;
import unisinos.tripverse.repository.UserRepository;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProvider userProvider;

    @Transactional(readOnly = true)
    public User getUser() {
        var auth = userProvider.getAuthenticatedUser();
        return userRepository.findById(auth.getId()).orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<User> getUser(int take, int skip) {

        int page = skip / take;

        return userRepository.findAll(PageRequest.of(page, take, Sort.by("id").ascending()));
    }

    @Transactional
    public User updateUser(UpdateUserDto update) {
        var user = getUser();

        user.setName(update.getName());

        user.setEmail(update.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public User removeUser() {
        var user = getUser();
        userRepository.delete(user);
        return user;
    }
}
