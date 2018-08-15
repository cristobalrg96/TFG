-- ----------------------------------------------------------------------------
-- Model
-------------------------------------------------------------------------------

-- ------------------------------ Table Drops ---------------------------------
DROP TABLE VoteComment;
DROP TABLE VoteEpisode;
DROP TABLE VoteMovie;
DROP TABLE VotePeople;
DROP TABLE VoteSeason;
DROP TABLE VoteTvShow;
DROP TABLE TvShowUser;
DROP TABLE RecommendationMovie;
DROP TABLE RecommendationPeople;
DROP TABLE RecommendationTvShow;
DROP TABLE PeopleUser;
DROP TABLE MovieUser;
DROP TABLE GenreTvShow;
DROP TABLE GenreMovie;
DROP TABLE EpisodeUser;
DROP TABLE CommentTvShow;
DROP TABLE CommentMovie;
DROP TABLE CommentPeople;
DROP TABLE CommentSeason;
DROP TABLE CommentEpisode;
DROP TABLE Response;
DROP TABLE Comment;
DROP TABLE MoviePeople; 
DROP TABLE TvShowPeople;
DROP TABLE Genre;
DROP TABLE Episode;
DROP TABLE Season;
DROP TABLE TvShow;
DROP TABLE Movie;
DROP TABLE People;
DROP TABLE User;

-- ------------------------------ TvShow ---------------------------------
CREATE TABLE TvShow (
	tvShowId BIGINT NOT NULL AUTO_INCREMENT,
	tvShowTitle VARCHAR(50) NOT NULL,
	tvShowPicture VARCHAR(100),
	tvShowTrailer VARCHAR(30),
	tvShowDuration INT,
	tvShowSummary VARCHAR(1500) NOT NULL,
	tvShowStartDate DATE NOT NULL,
	tvShowApiId BIGINT NOT NULL,
	tvShowStatus VARCHAR(30) NOT NULL,
	CONSTRAINT tvShowIdPK PRIMARY KEY(tvShowId))
	ENGINE = InnoDB;
	
CREATE INDEX TvShowIndexByTitle ON TvShow (tvShowTitle);


-- ------------------------------ Movie ---------------------------------
CREATE TABLE Movie (
	movieId BIGINT NOT NULL AUTO_INCREMENT,
	movieTitle VARCHAR(30) NOT NULL,
	moviePicture VARCHAR(100),
	movieTrailer VARCHAR(30),
	movieDuration INT,
	movieSummary VARCHAR(1500) NOT NULL,
	movieReleaseDate DATE NOT NULL,
	movieApiId BIGINT NOT NULL,
	movieStatus VARCHAR(15) NOT NULL,
	movieCost DECIMAL NOT NULL,
	CONSTRAINT movieIdPK PRIMARY KEY(movieId))
	ENGINE = InnoDB;
	
CREATE INDEX MovieIndexByTitle ON Movie (movieTitle);
	

-- ------------------------------ People ---------------------------------
CREATE TABLE PEOPLE (
	peopleId BIGINT NOT NULL AUTO_INCREMENT,
	peopleName VARCHAR(50) NOT NULL,
	peoplePicture VARCHAR(100),
	peopleBiography VARCHAR(5000),
	peopleApiId BIGINT NOT NULL,
	peopleBirthday DATE,
	peopleDeathday DATE,
	peoplePlaceOfBirth VARCHAR(100),
	CONSTRAINT peopleIdPK PRIMARY KEY(peopleId))
	ENGINE = InnoDB;	
	
CREATE INDEX PeopleIndexByName ON People (peopleName);


-- ------------------------------ Season ---------------------------------
CREATE TABLE Season (
	seasonId BIGINT NOT NULL AUTO_INCREMENT,
	seasonNumber INT NOT NULL,
	seasonTvShow BIGINT NOT NULL,
	seasonPicture VARCHAR(100),
	seasonSummary VARCHAR(1000),
	seasonReleaseDate DATE,
	seasonTrailer VARCHAR (30),
	CONSTRAINT seasonIdPK PRIMARY KEY(seasonId),
	CONSTRAINT seasonTvShowFK FOREIGN KEY(seasonTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ Episode ---------------------------------
CREATE TABLE Episode (
	episodeId BIGINT NOT NULL AUTO_INCREMENT,
	episodeNumber INT NOT NULL,
	episodeName VARCHAR(50),
	episodeSeason BIGINT NOT NULL,
	episodePicture VARCHAR(100),
	episodeSummary VARCHAR(1500),
	episodeReleaseDate DATE,
	episodeTrailer VARCHAR (30),
	CONSTRAINT episodeIdPK PRIMARY KEY(episodeId),
	CONSTRAINT episodeSeasonFK FOREIGN KEY(episodeSeason)
		REFERENCES Season(seasonId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ User ---------------------------------
CREATE TABLE User (
	userId BIGINT NOT NULL AUTO_INCREMENT,
	userLogin VARCHAR(30) NOT NULL,
	userEmail VARCHAR(30) NOT NULL,
	userPassword VARCHAR(40) NOT NULL,
	userPicture TEXT(50000) NOT NULL,
	userCreationDate DATE NOT NULL,
	userPrivacy BOOLEAN NOT NULL,
	CONSTRAINT userIdPK PRIMARY KEY(userId))
	ENGINE = InnoDB;	
	
CREATE INDEX UserIndexByLogin ON User (userLogin);
	

-- ------------------------------ Genre ---------------------------------
CREATE TABLE Genre (
	genreId BIGINT NOT NULL AUTO_INCREMENT,
	genreApiId BIGINT NOT NULL,
	genreName VARCHAR(25),
	CONSTRAINT genreIdPK PRIMARY KEY(genreId))
	ENGINE = InnoDB;
	
	
-- ------------------------------ TvShowPeople ---------------------------------
CREATE TABLE TvShowPeople (
	tvShowPeopleId BIGINT NOT NULL AUTO_INCREMENT,
	tvShowPeopleTvShow BIGINT NOT NULL,
	tvShowPeoplePeople BIGINT NOT NULL,
	tvShowPeopleJob VARCHAR(50) NOT NULL,
	tvShowPeopleCharacter VARCHAR(100),
	CONSTRAINT tvShowPeopleIdPK PRIMARY KEY(tvShowPeopleId),
	CONSTRAINT tvShowPeopleTvShowFK FOREIGN KEY(tvShowPeopleTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION,
	CONSTRAINT tvShowPeoplePeopleFK FOREIGN KEY(tvShowPeoplePeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ MoviePeople ---------------------------------
CREATE TABLE MoviePeople (
	moviePeopleId BIGINT NOT NULL AUTO_INCREMENT,
	moviePeopleMovie BIGINT NOT NULL,
	moviePeoplePeople BIGINT NOT NULL,
	moviePeopleJob VARCHAR(50) NOT NULL,
	moviePeopleCharacter VARCHAR(100),
	CONSTRAINT moviePeopleIdPK PRIMARY KEY(moviePeopleId),
	CONSTRAINT moviePeopleMovieFK FOREIGN KEY(moviePeopleMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION,
	CONSTRAINT moviePeoplePeopleFK FOREIGN KEY(moviePeoplePeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ Comment ---------------------------------
CREATE TABLE Comment (
	commentId BIGINT NOT NULL AUTO_INCREMENT,
	commentUser BIGINT NOT NULL,
	commentText VARCHAR(5000) NOT NULL,
	commentDate DATE NOT NULL,	
	CONSTRAINT commentIdPK PRIMARY KEY(commentId),
	CONSTRAINT commentUserFK FOREIGN KEY(commentUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ Response ---------------------------------
CREATE TABLE Response (
	responseId BIGINT NOT NULL AUTO_INCREMENT,
	responseReference BIGINT NOT NULL,
	responseComment BIGINT NOT NULL,
	CONSTRAINT responseIdPK PRIMARY KEY(responseId),
	CONSTRAINT responseReferenceFK FOREIGN KEY(responseReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT responseCommentFK FOREIGN KEY(responseComment)
		REFERENCES Comment(commentId) ON DELETE NO ACTION)
	ENGINE = InnoDB;	
	
	
-- ------------------------------ CommentEpisode ---------------------------------
CREATE TABLE CommentEpisode (
	commentEpisodeId BIGINT NOT NULL AUTO_INCREMENT,
	commentEpisodeReference BIGINT NOT NULL,
	commentEpisodeEpisode BIGINT NOT NULL,
	CONSTRAINT commentEpisodePK PRIMARY KEY(commentEpisodeId),
	CONSTRAINT commentEpisodeReferenceFK FOREIGN KEY(commentEpisodeReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT commentEpisodeEpisodeFK FOREIGN KEY(commentEpisodeEpisode)
		REFERENCES Episode(episodeId) ON DELETE NO ACTION)
	ENGINE = InnoDB;	
	

-- ------------------------------ CommentSeason ---------------------------------
CREATE TABLE CommentSeason (
	commentSeasonId BIGINT NOT NULL AUTO_INCREMENT,
	commentSeasonReference BIGINT NOT NULL,
	commentSeasonSeason BIGINT NOT NULL,
	CONSTRAINT commentSeasonPK PRIMARY KEY(commentSeasonId),
	CONSTRAINT commentSeasonReferenceFK FOREIGN KEY(commentSeasonReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT commentSeasonSeasonFK FOREIGN KEY(commentSeasonSeason)
		REFERENCES Season(seasonId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ CommentPeople ---------------------------------
CREATE TABLE CommentPeople (
	commentPeopleId BIGINT NOT NULL AUTO_INCREMENT,
	commentPeopleReference BIGINT NOT NULL,
	commentPeoplePeople BIGINT NOT NULL,
	CONSTRAINT commentPeoplePK PRIMARY KEY(commentPeopleId),
	CONSTRAINT commentPeopleReferenceFK FOREIGN KEY(commentPeopleReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT commentPeoplePeopleFK FOREIGN KEY(commentPeoplePeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ CommentMovie ---------------------------------
CREATE TABLE CommentMovie (
	commentMovieId BIGINT NOT NULL AUTO_INCREMENT,
	commentMovieReference BIGINT NOT NULL,
	commentMovieMovie BIGINT NOT NULL,
	CONSTRAINT commentMoviePK PRIMARY KEY(commentMovieId),
	CONSTRAINT commentMovieReferenceFK FOREIGN KEY(commentMovieReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT commentMovieMovieFK FOREIGN KEY(commentMovieMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ CommentTvShow ---------------------------------
CREATE TABLE CommentTvShow (
	commentTvShowId BIGINT NOT NULL AUTO_INCREMENT,
	commentTvShowReference BIGINT NOT NULL,
	commentTvShowTvShow BIGINT NOT NULL,
	CONSTRAINT commentTvShowPK PRIMARY KEY(commentTvShowId),
	CONSTRAINT commentTvShowReferenceFK FOREIGN KEY(commentTvShowReference)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT commentTvShowTvShowFK FOREIGN KEY(commentTvShowTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION)
	ENGINE = InnoDB;	
	
	
-- ------------------------------ EpisodeUser ---------------------------------
CREATE TABLE EpisodeUser (
	episodeUserId BIGINT NOT NULL AUTO_INCREMENT,
	episodeUserUser BIGINT NOT NULL,
	episodeUserEpisode BIGINT NOT NULL,
	episodeUserStatus VARCHAR(10) NOT NULL,
	episodeUserDate DATE NOT NULL,
	CONSTRAINT episodeUserIdPK PRIMARY KEY(episodeUserId),
	CONSTRAINT episodeUserUserFK FOREIGN KEY(episodeUserUser)
		REFERENCES User(userId) ON DELETE NO ACTION,
	CONSTRAINT episodeUserEpisodeFK FOREIGN KEY(episodeUserEpisode)
		REFERENCES Episode(episodeId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ GenreMovie ---------------------------------
CREATE TABLE GenreMovie (
	genreMovieId BIGINT NOT NULL AUTO_INCREMENT,
	genreMovieMovie BIGINT NOT NULL,
	genreMovieGenre BIGINT NOT NULL,
	CONSTRAINT genreMovieIdPK PRIMARY KEY(genreMovieId),
	CONSTRAINT genreMovieMovieFK FOREIGN KEY(genreMovieMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION,
	CONSTRAINT genreMovieGenreFK FOREIGN KEY(genreMovieGenre)
		REFERENCES Genre(genreId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ GenreTvShow ---------------------------------
CREATE TABLE GenreTvShow (
	genreTvShowId BIGINT NOT NULL AUTO_INCREMENT,
	genreTvShowTvShow BIGINT NOT NULL,
	genreTvShowGenre BIGINT NOT NULL,
	CONSTRAINT genreTvShowIdPK PRIMARY KEY(genreTvShowId),
	CONSTRAINT genreTvShowTvShowFK FOREIGN KEY(genreTvShowTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION,
	CONSTRAINT genreTvShowGenreFK FOREIGN KEY(genreTvShowGenre)
		REFERENCES Genre(genreId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ MovieUser ---------------------------------
CREATE TABLE MovieUser (
	movieUserId BIGINT NOT NULL AUTO_INCREMENT,
	movieUserMovie BIGINT NOT NULL,
	movieUserUser BIGINT NOT NULL,
	movieUserStatus VARCHAR(10) NOT NULL,
	movieUserDate DATE NOT NULL,
	CONSTRAINT movieUserIdPK PRIMARY KEY(movieUserId),
	CONSTRAINT movieUserMovieFK FOREIGN KEY(movieUserMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION,
	CONSTRAINT movieUserUserFK FOREIGN KEY(movieUserUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ PeopleUser ---------------------------------
CREATE TABLE PeopleUser (
	peopleUserId BIGINT NOT NULL AUTO_INCREMENT,
	peopleUserPeople BIGINT NOT NULL,
	peopleUserUser BIGINT NOT NULL,
	peopleUserStatus VARCHAR(10) NOT NULL,
	peopleUserDate DATE NOT NULL,
	CONSTRAINT peopleUserIdPK PRIMARY KEY(peopleUserId),
	CONSTRAINT peopleUserPeopleFK FOREIGN KEY(peopleUserPeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION,
	CONSTRAINT peopleUserUserFK FOREIGN KEY(peopleUserUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;	
	
	
-- ------------------------------ RecommendationTvShow ---------------------------------
CREATE TABLE RecommendationTvShow (
	recommendationTvShowId BIGINT NOT NULL AUTO_INCREMENT,
	recommendationTvShowTvShow BIGINT NOT NULL,
	recommendationTvShowRecommender BIGINT NOT NULL,
	recommendationTvShowRecommended BIGINT NOT NULL,
	recommendationTvShowDate DATE NOT NULL,
	CONSTRAINT recommendationTvShowIdPK PRIMARY KEY(recommendationTvShowId),
	CONSTRAINT recommendationTvShowTvShowFK FOREIGN KEY(recommendationTvShowTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION,
	CONSTRAINT recommendationTvShowRecommenderFK FOREIGN KEY(recommendationTvShowRecommender)
		REFERENCES User(userId) ON DELETE NO ACTION,
	CONSTRAINT recommendationTvShowRecommendedFK FOREIGN KEY(recommendationTvShowRecommended)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ RecommendationPeople ---------------------------------
CREATE TABLE RecommendationPeople (
	recommendationPeopleId BIGINT NOT NULL AUTO_INCREMENT,
	recommendationPeoplePeople BIGINT NOT NULL,
	recommendationPeopleRecommender BIGINT NOT NULL,
	recommendationPeopleRecommended BIGINT NOT NULL,
	recommendationPeopleDate DATE NOT NULL,
	CONSTRAINT recommendationPeopleIdPK PRIMARY KEY(recommendationPeopleId),
	CONSTRAINT recommendationPeoplePeopleFK FOREIGN KEY(recommendationPeoplePeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION,
	CONSTRAINT recommendationPeopleRecommenderFK FOREIGN KEY(recommendationPeopleRecommender)
		REFERENCES User(userId) ON DELETE NO ACTION,
	CONSTRAINT recommendationPeopleRecommendedFK FOREIGN KEY(recommendationPeopleRecommended)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;

	
-- ------------------------------ RecommendationMovie ---------------------------------
CREATE TABLE RecommendationMovie (
	recommendationMovieId BIGINT NOT NULL AUTO_INCREMENT,
	recommendationMovieMovie BIGINT NOT NULL,
	recommendationMovieRecommender BIGINT NOT NULL,
	recommendationMovieRecommended BIGINT NOT NULL,
	recommendationMovieDate DATE NOT NULL,
	CONSTRAINT recommendationMovieIdPK PRIMARY KEY(recommendationMovieId),
	CONSTRAINT recommendationMovieMovieFK FOREIGN KEY(recommendationMovieMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION,
	CONSTRAINT recommendationMovieRecommenderFK FOREIGN KEY(recommendationMovieRecommender)
		REFERENCES User(userId) ON DELETE NO ACTION,
	CONSTRAINT recommendationMovieRecommendedFK FOREIGN KEY(recommendationMovieRecommended)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ TvShowUser ---------------------------------
CREATE TABLE TvShowUser (
	tvShowUserId BIGINT NOT NULL AUTO_INCREMENT,
	tvShowUserTvShow BIGINT NOT NULL,
	tvShowUserUser BIGINT NOT NULL,
	tvShowUserStatus VARCHAR(10) NOT NULL,
	tvShowUserDate DATE NOT NULL,
	CONSTRAINT tvShowUserIdPK PRIMARY KEY(tvShowUserId),
	CONSTRAINT tvShowUserTvShowFK FOREIGN KEY(tvShowUserTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION,
	CONSTRAINT tvShowUserUserFK FOREIGN KEY(tvShowUserUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	

-- ------------------------------ VoteTvShow ---------------------------------
CREATE TABLE VoteTvShow (
	voteTvShowId BIGINT NOT NULL AUTO_INCREMENT,
	voteTvShowVote BOOLEAN NOT NULL,
	voteTvShowTvShow BIGINT NOT NULL,
	voteTvShowUser BIGINT NOT NULL,
	voteTvShowDate DATE NOT NULL,
	CONSTRAINT voteTvShowIdPK PRIMARY KEY(voteTvShowId),
	CONSTRAINT voteTvShowTvShowFK FOREIGN KEY(voteTvShowTvShow)
		REFERENCES TvShow(tvShowId) ON DELETE NO ACTION,
	CONSTRAINT voteTvShowUserFK FOREIGN KEY(voteTvShowUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ VoteSeason ---------------------------------
CREATE TABLE VoteSeason (
	voteSeasonId BIGINT NOT NULL AUTO_INCREMENT,
	voteSeasonVote BOOLEAN NOT NULL,
	voteSeasonSeason BIGINT NOT NULL,
	voteSeasonUser BIGINT NOT NULL,
	voteSeasonDate DATE NOT NULL,
	CONSTRAINT voteSeasonIdPK PRIMARY KEY(voteSeasonId),
	CONSTRAINT voteSeasonSeasonFK FOREIGN KEY(voteSeasonSeason)
		REFERENCES Season(seasonId) ON DELETE NO ACTION,
	CONSTRAINT voteSeasonUserFK FOREIGN KEY(voteSeasonUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ VotePeople ---------------------------------
CREATE TABLE VotePeople (
	votePeopleId BIGINT NOT NULL AUTO_INCREMENT,
	votePeopleVote BOOLEAN NOT NULL,
	votePeoplePeople BIGINT NOT NULL,
	votePeopleUser BIGINT NOT NULL,
	votePeopleDate DATE NOT NULL,
	CONSTRAINT votePeopleIdPK PRIMARY KEY(votePeopleId),
	CONSTRAINT votePeoplePeopleFK FOREIGN KEY(votePeoplePeople)
		REFERENCES People(peopleId) ON DELETE NO ACTION,
	CONSTRAINT votePeopleUserFK FOREIGN KEY(votePeopleUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ VoteMovie ---------------------------------
CREATE TABLE VoteMovie (
	voteMovieId BIGINT NOT NULL AUTO_INCREMENT,
	voteMovieVote BOOLEAN NOT NULL,
	voteMovieMovie BIGINT NOT NULL,
	voteMovieUser BIGINT NOT NULL,
	voteMovieDate DATE NOT NULL,
	CONSTRAINT voteMovieIdPK PRIMARY KEY(voteMovieId),
	CONSTRAINT voteMovieMovieFK FOREIGN KEY(voteMovieMovie)
		REFERENCES Movie(movieId) ON DELETE NO ACTION,
	CONSTRAINT voteMovieUserFK FOREIGN KEY(voteMovieUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ VoteEpisode ---------------------------------
CREATE TABLE VoteEpisode (
	voteEpisodeId BIGINT NOT NULL AUTO_INCREMENT,
	voteEpisodeVote BOOLEAN NOT NULL,
	voteEpisodeEpisode BIGINT NOT NULL,
	voteEpisodeUser BIGINT NOT NULL,
	voteEpisodeDate DATE NOT NULL,
	CONSTRAINT voteEpisodeIdPK PRIMARY KEY(voteEpisodeId),
	CONSTRAINT voteEpisodeEpisodeFK FOREIGN KEY(voteEpisodeEpisode)
		REFERENCES Episode(episodeId) ON DELETE NO ACTION,
	CONSTRAINT voteEpisodeUserFK FOREIGN KEY(voteEpisodeUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;
	
	
-- ------------------------------ VoteComment ---------------------------------
CREATE TABLE VoteComment (
	voteCommentId BIGINT NOT NULL AUTO_INCREMENT,
	voteCommentVote BOOLEAN NOT NULL,
	voteCommentComment BIGINT NOT NULL,
	voteCommentUser BIGINT NOT NULL,
	voteCommentDate DATE NOT NULL,
	CONSTRAINT voteCommentIdPK PRIMARY KEY(voteCommentId),
	CONSTRAINT voteCommentCommentFK FOREIGN KEY(voteCommentComment)
		REFERENCES Comment(commentId) ON DELETE NO ACTION,
	CONSTRAINT voteCommentUserFK FOREIGN KEY(voteCommentUser)
		REFERENCES User(userId) ON DELETE NO ACTION)
	ENGINE = InnoDB;