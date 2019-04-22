package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="matches", uniqueConstraints=@UniqueConstraint(columnNames={"match_id", "user_Id"}))
public class Match {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fav_id")
	private int favId;
	
	@Column(name="match_id")
	private String id;
	
	@Column(name ="user_id")
	private  String userId;
	
	@Column(name = "game_mode")
	private String gameMode;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "map_name")
	private String mapName;
	
	@Column(name = "title_id")
	private String titleId;
	

	
	
	
	
	



	




	public Match(int favId, String id, String userId, String gameMode, String createdAt, String mapName,String titleId) {
		super();
		this.favId = favId;
		this.id = id;
		this.userId = userId;
		this.gameMode = gameMode;
		this.createdAt = createdAt;
		this.mapName = mapName;
		this.titleId = titleId;
	}















	














	@Override
	public String toString() {
		return "Match [favId=" + favId + ", id=" + id + ", userId=" + userId + ", gameMode=" + gameMode + ", createdAt="
				+ createdAt + ", mapName=" + mapName + ", titleId=" + titleId + "]";
	}






























	public int getFavId() {
		return favId;
	}















	public void setFavId(int favId) {
		this.favId = favId;
	}















	





























	public String getId() {
		return id;
	}






























	public void setId(String id) {
		this.id = id;
	}






























	public String getUserId() {
		return userId;
	}















	public void setUserId(String userId) {
		this.userId = userId;
	}















	public String getGameMode() {
		return gameMode;
	}















	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}















	public String getCreatedAt() {
		return createdAt;
	}















	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}















	public String getMapName() {
		return mapName;
	}















	public void setMapName(String mapName) {
		this.mapName = mapName;
	}















	public String getTitleId() {
		return titleId;
	}















	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}















	public Match() {
		// TODO Auto-generated constructor stub
	}
	

}
