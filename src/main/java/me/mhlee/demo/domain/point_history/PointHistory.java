package me.mhlee.demo.domain.point_history;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POINT_HISTORY")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "`type`")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public static PointHistory of(@NonNull Long userId, @NonNull Type type, @NonNull Long amount, @NonNull Long afterBalance) {
        return PointHistory.builder()
                .userId(userId)
                .type(type)
                .amount(amount)
                .balance(afterBalance)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    @Getter
    public enum Type {
        CREATED("생성"),
        PLUS("추가"),
        MINUS("차감 ");

        private String desc;

        Type(String desc) {
            this.desc = desc;
        }
    }
}

