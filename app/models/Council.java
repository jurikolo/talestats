package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Council extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	private String cityId;

	@Basic
	@Required
	private String name;

	@Basic
	@Required
	private String job;
	
	@Basic
	@Required
	private String race;
	
	@Basic
	@Required
	private Integer allies;
	
	@Basic
	@Required
	private Integer enemies;
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityId() {
		return cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Integer getAllies() {
		return allies;
	}

	public void setAllies(Integer allies) {
		this.allies = allies;
	}

	public Integer getEnemies() {
		return enemies;
	}

	public void setEnemies(Integer enemies) {
		this.enemies = enemies;
	}

	@Override
	public String toString() {
		return "City [key=" + key + ", name=" + name + "]";
	}
}
