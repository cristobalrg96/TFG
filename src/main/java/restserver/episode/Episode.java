package restserver.episode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import restserver.season.Season;

@Entity
@BatchSize(size=50)
public class Episode {
	
	private Long episodeId;
	private int episodeNumber;
	private String episodeName;
	private Season episodeSeason;
	private String episodePicture;
	private String episodeSummary;
	private Date episodeReleaseDate;
	private String episodeTrailer;
	
	public Episode(int episodeNumber, String episodeName, Season episodeSeason, String episodePicture, 
			String episodeSummary, Date episodeReleaseDate, String episodeTrailer) {
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.episodeSeason = episodeSeason;
		this.episodePicture = episodePicture;
		this.episodeSummary = episodeSummary;
		this.episodeReleaseDate = episodeReleaseDate;
		this.episodeTrailer = episodeTrailer;
	}
	
	public Episode() {
	}

	@SequenceGenerator( // It only takes effect for
		name = "EpisodeIdGenerator", // databases providing identifier
		sequenceName = "EpisodeIdSeq")
		// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EpisodeIdGenerator")
	public Long getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Long episodeId) {
		this.episodeId = episodeId;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="episodeSeason")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public Season getEpisodeSeason() {
		return episodeSeason;
	}

	public void setEpisodeSeason(Season episodeSeason) {
		this.episodeSeason = episodeSeason;
	}

	public String getEpisodePicture() {
		return episodePicture;
	}

	public void setEpisodePicture(String episodePicture) {
		this.episodePicture = episodePicture;
	}

	public String getEpisodeSummary() {
		return episodeSummary;
	}

	public void setEpisodeSummary(String episodeSummary) {
		this.episodeSummary = episodeSummary;
	}

	public Date getEpisodeReleaseDate() {
		return episodeReleaseDate;
	}

	public void setEpisodeReleaseDate(Date episodeReleaseDate) {
		this.episodeReleaseDate = episodeReleaseDate;
	}

	public String getEpisodeTrailer() {
		return episodeTrailer;
	}

	public void setEpisodeTrailer(String episodeTrailer) {
		this.episodeTrailer = episodeTrailer;
	}

}
