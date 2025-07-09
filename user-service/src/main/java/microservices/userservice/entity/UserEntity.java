package microservices.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "user_service")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_service.user_id_seq")
    @SequenceGenerator(name = "user_service.user_id_seq", sequenceName = "user_service.user_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String email;
    private String password;
}