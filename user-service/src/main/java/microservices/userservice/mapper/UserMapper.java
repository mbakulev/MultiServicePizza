package microservices.userservice.mapper;


import microservices.userservice.entity.UserEntity;
import model.UserDTO;

public class UserMapper {
    public static UserDTO toDto(UserEntity entity) {
        if (entity == null) return null;

        UserDTO userDto = UserDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();

        return userDto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        if (dto == null) return null;


        UserEntity userEntity = new UserEntity();

        userEntity.setId(dto.getId());
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());

        return userEntity;
    }
}
