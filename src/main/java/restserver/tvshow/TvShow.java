package restserver.tvshow;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import restserver.genre_tvshow.GenreTvShow;
import restserver.season.Season;
import restserver.tvshow_people.TvShowPeople;

@Entity
@BatchSize(size=50)
public class TvShow {

	private Long tvShowId;
	private String tvShowTitle;
	private String tvShowPicture;
	private String tvShowTrailer;
	private int tvShowDuration;
	private String tvShowSummary;
	private Date tvShowStartDate;
	private Long tvShowApiId;
	private String tvShowStatus;
	private List<Season> tvShowSeasons;
	private List<TvShowPeople> tvShowCredits;
	private List<GenreTvShow> tvShowGenres;
	
	public TvShow(String tvShowTitle, String tvShowPicture, String tvShowTrailer, int tvShowDuration,
			String tvShowSummary, Date tvShowStartDate, Long tvShowApiId, String tvShowStatus) {
		this.tvShowTitle = tvShowTitle;
		this.tvShowPicture = tvShowPicture;
		this.tvShowTrailer = tvShowTrailer;
		this.tvShowDuration = tvShowDuration;
		this.tvShowSummary = tvShowSummary;
		this.tvShowStartDate = tvShowStartDate;
		this.tvShowApiId = tvShowApiId;
		this.tvShowStatus = tvShowStatus;
	}
	
	public TvShow() {
	}

	@SequenceGenerator( // It only takes effect for
	name = "TvShowIdGenerator", // databases providing identifier
	sequenceName = "TvShowIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TvShowIdGenerator")
	public Long getTvShowId() {
		return tvShowId;
	}
	
	public void setTvShowId(Long tvShowId) {
		this.tvShowId = tvShowId;
	}
	
	public String getTvShowTitle() {
		return tvShowTitle;
	}

	public void setTvShowTitle(String tvShowTitle) {
		this.tvShowTitle = tvShowTitle;
	}

	public String getTvShowPicture() {
		return tvShowPicture;
	}

	public void setTvShowPicture(String tvShowPicture) {
		this.tvShowPicture = tvShowPicture;
	}

	public String getTvShowTrailer() {
		return tvShowTrailer;
	}

	public void setTvShowTrailer(String tvShowTrailer) {
		this.tvShowTrailer = tvShowTrailer;
	}

	public int getTvShowDuration() {
		return tvShowDuration;
	}

	public void setTvShowDuration(int tvShowDuration) {
		this.tvShowDuration = tvShowDuration;
	}

	public String getTvShowSummary() {
		return tvShowSummary;
	}

	public void setTvShowSummary(String tvShowSummary) {
		this.tvShowSummary = tvShowSummary;
	}

	public Date getTvShowStartDate() {
		return tvShowStartDate;
	}

	public void setTvShowStartDate(Date tvShowStartDate) {
		this.tvShowStartDate = tvShowStartDate;
	}

	public Long getTvShowApiId() {
		return tvShowApiId;
	}

	public void setTvShowApiId(Long tvShowApiId) {
		this.tvShowApiId = tvShowApiId;
	}

	public String getTvShowStatus() {
		return tvShowStatus;
	}

	public void setTvShowStatus(String tvShowStatus) {
		this.tvShowStatus = tvShowStatus;
	}

	@OneToMany(mappedBy="seasonTvShow")
	public List<Season> getTvShowSeasons() {
		return tvShowSeasons;
	}

	public void setTvShowSeasons(List<Season> tvShowSeasons) {
		this.tvShowSeasons = tvShowSeasons;
	}

	@OneToMany(mappedBy="tvShowPeopleTvShow")
	public List<TvShowPeople> getTvShowCredits() {
		return tvShowCredits;
	}

	public void setTvShowCredits(List<TvShowPeople> tvShowCredits) {
		this.tvShowCredits = tvShowCredits;
	}

	@OneToMany(mappedBy="genreTvShowTvShow")
	public List<GenreTvShow> getTvShowGenres() {
		return tvShowGenres;
	}

	public void setTvShowGenres(List<GenreTvShow> tvShowGenres) {
		this.tvShowGenres = tvShowGenres;
	}

}
