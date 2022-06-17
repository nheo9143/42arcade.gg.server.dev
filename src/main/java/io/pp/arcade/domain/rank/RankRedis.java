package io.pp.arcade.domain.rank;

import io.pp.arcade.domain.user.User;
import io.pp.arcade.global.util.RacketType;
import io.pp.arcade.global.util.GameType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@RedisHash("user")
@Getter
@NoArgsConstructor
public class RankRedis implements Serializable {

    @Id
    private String id;

    @Indexed
    private String intraId;

    private Integer ppp;

    private RacketType racketType;

    @Indexed
    private GameType gameType;

    private Integer wins;

    private Integer losses;

    private double winRate;

    @Setter
    private String statusMessage;


    @Builder
    public RankRedis(String intraId, Integer ppp, RacketType racketType, GameType gameType, Integer wins, Integer losses, double winRate, String statusMessage) {
        this.intraId = intraId;
        this.ppp = ppp;
        this.racketType = racketType;
        this.gameType = gameType;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.statusMessage = statusMessage;
    }

    public static RankRedis from (User user, GameType gameType){
        return RankRedis.builder()
                .intraId(user.getIntraId())
                .ppp(user.getPpp())
                .statusMessage(user.getStatusMessage())
                .gameType(gameType)
                .racketType(user.getRacketType())
                .wins(0)
                .losses(0)
                .winRate(0)
                .build();
    }

    public void update(Boolean isWin, Integer ppp){
        if (isWin == true) {
            this.wins++;
        } else {
            this.losses++;
        }
        this.ppp = ppp;
        this.winRate = wins / (double)(wins + losses);
    }
}
