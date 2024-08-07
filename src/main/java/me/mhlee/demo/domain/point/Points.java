package me.mhlee.demo.domain.point;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.common.exceptions.ErrorCode;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POINTS")
public class Points {
    private static final Long ZERO = 0L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "bef_balance", nullable = false)
    private Long beforeBalance;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public void plus(Long amount) {
        this.beforeBalance = balance;
        this.balance += amount;
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public void minus(Long amount) {
        if (balance < amount) {
            throw new ApiException(ErrorCode.NOT_ENOUGH_POINT.toMessage(this.balance, amount));
        }

        this.beforeBalance = balance;
        this.balance -= amount;
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static Points of(Long userId) {
        return Points.builder()
                .userId(userId)
                .balance(ZERO)
                .beforeBalance(ZERO)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public class Vo {
        private Long userId;
        private Long balance;
        private Long beforeBalance;
        private Timestamp createdAt;
        private Timestamp updatedAt;
    }

    public Vo toVo() {
        return new Vo()
                .setUserId(userId)
                .setBalance(balance)
                .setBeforeBalance(beforeBalance)
                .setCreatedAt(createdAt)
                .setUpdatedAt(updatedAt);
    }
}
