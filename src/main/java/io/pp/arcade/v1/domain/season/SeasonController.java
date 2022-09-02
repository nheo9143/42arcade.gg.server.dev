package io.pp.arcade.v1.domain.season;

import io.pp.arcade.v1.domain.season.dto.SeasonDto;
import io.pp.arcade.v1.domain.season.dto.SeasonListResponseDto;
import io.pp.arcade.v1.domain.season.dto.SeasonNameDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/pingpong")
@AllArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping(value = "/seasonlist")
    public SeasonListResponseDto getSeasonList() {
        List<SeasonNameDto> seasons = seasonService.findAllSeason();

        SeasonListResponseDto responseDto = SeasonListResponseDto.builder().seasonList(seasons).build();
        return responseDto;
    }
}