package me.maximelucas.nonbinaires.home.entities;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "homes")
public class Home {
	@DatabaseField(generatedId = true)
	private UUID id;

	@DatabaseField(canBeNull = false)
	private String name;

	@DatabaseField(canBeNull = false)
	private String player;

	@DatabaseField(canBeNull = false)
	private Double posX;

	@DatabaseField(canBeNull = false)
	private Double posY;

	@DatabaseField(canBeNull = false)
	private Double posZ;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}

	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}

	public Double getPosZ() {
		return posZ;
	}

	public void setPosZ(Double posZ) {
		this.posZ = posZ;
	}

}
