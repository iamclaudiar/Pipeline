package com.example.users.service;

import com.example.users.entity.Friend;
import com.example.users.entity.User;
import com.example.users.repository.FriendRepository;
import com.example.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRespository;

    public UserService(UserRepository userRepository, FriendRepository friendRespository) {
        this.userRepository = userRepository;
        this.friendRespository = friendRespository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addFriend(Long id, Friend friend) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        friend.setUser(user);
        friendRespository.save(friend);
        List<Friend> friends = user.getFriends();
        friends.add(friend);
        user.setFriends(friends);
        userRepository.save(user);
    }

    public List<Friend> getFriends(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFriends();
    }
}

