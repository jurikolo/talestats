package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Heroes extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@Required
	private int guildId;
	
	@Basic
	@Required
	private int ally;
	
	@Basic
	@Required
	private int enemy;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public int getAlly() {
		return ally;
	}

	public void setAlly(int ally) {
		this.ally = ally;
	}

	public int getEnemy() {
		return enemy;
	}

	public void setEnemy(int enemy) {
		this.enemy = enemy;
	}

	@Override
	public String toString() {
		return "City [key=" + key + ", name=" + name + "]";
	}
}
