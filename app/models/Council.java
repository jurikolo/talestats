package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Council extends Model implements BasicModel<Integer> {

	@Id
	private Integer key;

	@Basic
	@Required
	private Integer cityId;

	@Basic
	@Required
	private String name;

	@Basic
	private String job;
	
	@Basic
	private String race;
	
	@Basic
	private String skill;
	
	@Basic
	private Integer allies;
	
	@Basic
	private Integer enemies;
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getCityId() {
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
	
		public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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
		return "Council [key=" + key + ", name=" + name + "]";
	}
}
