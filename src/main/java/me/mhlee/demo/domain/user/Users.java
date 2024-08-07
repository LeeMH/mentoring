package me.mhlee.demo.domain.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login_id", columnDefinition = "varchar(16)", nullable = false)
    private String loginId;

    @Column(name = "password", columnDefinition = "varchar(255)", nullable = false)
    private String password;

    @Column(name = "name", columnDefinition = "varchar(16)", nullable = false)
    private String name;

    @Column(name = "age", columnDefinition = "tinyint", nullable = false)
    private int age;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public static Users of(String loginId, String encodedPassword, String name, int age) {
        return Users.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .name(name)
                .age(age)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public class Vo {
        private Long id;
        private String loginId;
        private String name;
        private int age;
        private Timestamp createdAt;
        private Timestamp updatedAt;
    }

    public Vo toVo() {
        return new Vo()
                .setId(id)
                .setLoginId(loginId)
                .setName(name)
                .setAge(age)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt);
    }
}
