package code;
import java.util.Locale;
import java.util.Objects;

// Raw height is in decimeters; weight is in hectograms.
public class Pokemon {
    private int id;
    private String name;
    private int heightDm;
    private int weightHg;
    private int baseExperience;

    public Pokemon(int id, String name, int heightDm, int weightHg, int baseExperience) {
        this.id = id;
        this.name = name;
        this.heightDm = heightDm;
        this.weightHg = weightHg;
        this.baseExperience = baseExperience;
    }

    public String getName() { 
    	return name; 
    }
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0)
			return;
		this.id = id;
	}

	public int getHeightDm() {
		return heightDm;
	}

	public void setHeightDm(int heightDm) {
		if (heightDm <= 0)
			return;
		this.heightDm = heightDm;
	}

	public int getWeightHg() {
		return weightHg;
	}

	public void setWeightHg(int weightHg) {
		if (weightHg <= 0)
		this.weightHg = weightHg;
	}

	public int getBaseExperience() {
		return baseExperience;
	}

	public void setBaseExperience(int baseExperience) {
		if (baseExperience < 0)
			return;
		this.baseExperience = baseExperience;
	}

	public void setName(String name) {
		//setName() rejects null or black names
		if(name == null || name == "")
			return;
		this.name = name.trim();
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(baseExperience), Integer.valueOf(heightDm), Integer.valueOf(id), name,
				Integer.valueOf(weightHg));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return baseExperience == other.baseExperience && heightDm == other.heightDm && id == other.id
				&& Objects.equals(name, other.name) && weightHg == other.weightHg;
	}

	@Override
    public String toString() {
        return String.format(Locale.US,
            "#%d %s | Height: %.1f m | Weight: %.1f kg | Base experience: %d",
            id, name, heightDm / 10.0, weightHg / 10.0, baseExperience);
    }
}
