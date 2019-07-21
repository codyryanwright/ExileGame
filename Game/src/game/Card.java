package game;

public class Card {
	private String type;
	private String archetype;
	private String rarity;
	private Float power;
	
	public Card(String type, String archetype, String rarity,
			Float power) {
		setType(type);
		setArchetype(archetype);
		setRarity(rarity);
		setPower(power);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getArchetype() {
		return archetype;
	}
	
	public void setArchetype(String archetype) {
		this.archetype = archetype;
	}
	
	public String getRarity() {
		return rarity;
	}
	
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	public Float getPower() {
		return power;
	}
	
	public void setPower(Float power) {
		this.power = power;
	}	
}
