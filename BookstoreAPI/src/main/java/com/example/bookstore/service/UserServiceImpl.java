package com.example.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found with id " + id) );
	}

	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		Optional<User> foundUser=userRepository.findById(id);
		if(foundUser.isPresent()) {
			User existingUser=foundUser.get();
			existingUser.setId(user.getId());
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			return userRepository.save(existingUser);
		}else {
            throw new NotFoundException("User not found with id " + id);
        }
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		
	}
}
