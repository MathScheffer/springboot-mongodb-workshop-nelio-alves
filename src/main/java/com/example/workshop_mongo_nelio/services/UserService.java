package com.example.workshop_mongo_nelio.services;

import com.example.workshop_mongo_nelio.domain.User;
import com.example.workshop_mongo_nelio.dto.UserDTO;
import com.example.workshop_mongo_nelio.repository.UserRepository;
import com.example.workshop_mongo_nelio.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> u = repository.findAll();
        List<UserDTO> userDTO = u.stream().map(UserDTO::new).toList();
        System.out.println(u);
        return userDTO;
    }

    public User findById(String id){
        User user = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("User not found"));
        return user;
    }

    public User insert(User user){
        return repository.insert(user);
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public void delete(String id){
        findById(id); //verifica se o user existe
        repository.deleteById(id);
    }

    public User update(String  id, User user){
        User dbUser = findById(id);
        updateData(dbUser, user);
        return repository.save(dbUser);
    }

    private void updateData(User dbUser, User updatedUser) {
        if(updatedUser.getName() != null) dbUser.setName(updatedUser.getName());
        if(updatedUser.getEmail() != null) dbUser.setEmail(updatedUser.getEmail());
    }
}
