package com.hairlesscat.app.tournamenttimeslot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.hairlesscat.app.round.Round;
import com.hairlesscat.app.team.Team;
import com.hairlesscat.app.tournament.Tournament;
import com.hairlesscat.app.view.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament_timeslot")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name="timeslotId", column=@Column(name="tournament_timeslot_id"))
public class TournamentTimeslot extends Timeslot {

    @ManyToMany
    @JoinTable(
            name = "tournamenttimeslot_team_map",
            joinColumns = @JoinColumn(
                    name = "tournament_timeslot_id",
                    referencedColumnName = "tournament_timeslot_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "team_id",
                    referencedColumnName = "team_id"
            )
    )
    @JsonProperty("available_teams")
    @JsonView(Views.TournamentTimeslot.class)
    private Set<Team> availableTeams = new HashSet<>();

    @ManyToOne
    @JoinColumn(
            name = "round_id",
            referencedColumnName = "round_id"
    )
    @JsonIgnore
    private Round round;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "tournament_id")
    @JsonIgnore
    private Tournament tournament;

    public TournamentTimeslot(LocalDateTime startTime, LocalDateTime endTime) {
        super(startTime, endTime);
    }

    /**
     * Checks if {@code team} is one of the teams available to play in this timeslot.
     * @param team the team to be checked.
     * @return {@code} true if {@code team} indicated that they can play for this timeslot.
     */
    public boolean hasTeam(Team team) {
        return availableTeams.contains(team);
    }

    public void addTeam(Team team) {
        availableTeams.add(team);
    }

    @Override
    @JsonProperty("tournament_timeslot_id")
    public Long getTimeslotId() {
        return super.getTimeslotId();
    }
}
