package com.tournament.app.round;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tournament.app.match.Match;
import com.tournament.app.schedule.Schedule;
import com.tournament.app.tournament.Tournament;
import com.tournament.app.tournamenttimeslot.TournamentTimeslot;
import com.tournament.app.view.Views;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "round")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Round {
	@Id
	@SequenceGenerator(
		name = "round_sequence_generator",
		sequenceName = "round_sequence_generator",
		allocationSize = 1
	)
	@GeneratedValue(
		generator = "round_sequence_generator",
		strategy = GenerationType.SEQUENCE
	)
	@Column(name = "round_id", updatable = false)
	@JsonProperty("round_id")
	@JsonView(Views.Public.class)
	private Long round_id;

	@ManyToOne
	@JoinColumn(name = "tournament_id", referencedColumnName = "tournament_id")
	@JsonProperty("round_tournament")
	@JsonView(Views.RoundSummary.class)
	private Tournament tournament;

	@OneToMany(
			mappedBy = "round",
			cascade = CascadeType.ALL
	)
	@JsonProperty("timeslots")
	@JsonView(Views.ScheduleFull.class)
	private List<TournamentTimeslot> timeslots;

	@OneToMany(
			mappedBy = "round",
			cascade = CascadeType.ALL
	)
	@JsonProperty("matches")
	@JsonView(Views.MatchSummary.class)
	private List<Match> matches;

	@ManyToOne
	@JoinColumn(
			name = "schedule_id",
			referencedColumnName = "schedule_id"
	)
	@JsonIgnore
	private Schedule schedule;
}
