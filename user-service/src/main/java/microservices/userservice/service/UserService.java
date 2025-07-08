package microservices.userservice.service;

import microservices.userservice.entity.UserEntity;
import microservices.userservice.exception.UserAlreadyExistsException;
import microservices.userservice.exception.UserNotFoundException;
import microservices.userservice.mapper.UserMapper;
import microservices.userservice.repository.UserRepository;
import model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            UserDTO userDTO = UserMapper.toDto(userEntity.get());
            return userDTO;
        } else throw new UserNotFoundException("User not found id: " + id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userDTO.getEmail());

        if (userEntityOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists: " + userDTO.getId());
        }
        UserEntity userEntity = UserMapper.toEntity(userDTO);
        userEntity.setPassword(String.valueOf(userDTO.getPassword().hashCode()));
        UserEntity saved = userRepository.save(userEntity);

        return UserMapper.toDto(saved);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
