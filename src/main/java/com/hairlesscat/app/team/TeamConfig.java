package com.tournament.app.team;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfig {
    @Bean("team_init")
    CommandLineRunner commandLineRunner(TeamRepository teamRepository) {
        return args -> {
        };
    }
}
