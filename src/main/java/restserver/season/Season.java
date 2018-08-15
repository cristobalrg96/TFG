package restserver.season;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import restserver.episode.Episode;
import restserver.tvshow.TvShow;

@Entity
@BatchSize(size=50)
public class Season {

	private Long seasonId;
	private int seasonNumber;
	private TvShow seasonTvShow;
	private String seasonPicture;
	private String seasonSummary;
	private Date seasonReleaseDate;
	private String seasonTrailer;
	private List<Episode> seasonEpisodes;
	
	public Season(int seasonNumber, TvShow seasonTvShow, String seasonPicture, String seasonSummary,
			Date seasonReleaseDate, String seasonTrailer) {
		this.seasonNumber = seasonNumber;
		this.seasonTvShow = seasonTvShow;
		this.seasonPicture = seasonPicture;
		this.seasonSummary = seasonSummary;
		this.seasonReleaseDate = seasonReleaseDate;
		this.seasonTrailer = seasonTrailer;
	}

	public Season() {
	}

	
	@SequenceGenerator( // It only takes effect for
	name = "SeasonIdGenerator", // databases providing identifier
	sequenceName = "SeasonIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeasonIdGenerator")
	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="seasonTvShow")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public TvShow getSeasonTvShow() {
		return seasonTvShow;
	}

	public void setSeasonTvShow(TvShow seasonTvShow) {
		this.seasonTvShow = seasonTvShow;
	}

	public String getSeasonPicture() {
		return seasonPicture;
	}

	public void setSeasonPicture(String seasonPicture) {
		this.seasonPicture = seasonPicture;
	}

	public String getSeasonSummary() {
		return seasonSummary;
	}

	public void setSeasonSummary(String seasonSummary) {
		this.seasonSummary = seasonSummary;
	}

	public Date getSeasonReleaseDate() {
		return seasonReleaseDate;
	}

	public void setSeasonReleaseDate(Date seasonReleaseDate) {
		this.seasonReleaseDate = seasonReleaseDate;
	}

	public String getSeasonTrailer() {
		return seasonTrailer;
	}

	public void setSeasonTrailer(String seasonTrailer) {
		this.seasonTrailer = seasonTrailer;
	}

	@OneToMany(mappedBy="episodeSeason")
	public List<Episode> getSeasonEpisodes() {
		return seasonEpisodes;
	}

	public void setSeasonEpisodes(List<Episode> seasonEpisodes) {
		this.seasonEpisodes = seasonEpisodes;
	}
}
