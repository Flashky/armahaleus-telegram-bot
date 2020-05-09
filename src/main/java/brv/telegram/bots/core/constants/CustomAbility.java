package brv.telegram.bots.core.constants;

public enum CustomAbility {
	START(""),
	HELP(""),
	PAW("Get a random kitty!");
	
	private String description;
	
	private CustomAbility(String description) {
		this.description = description;
	}
	
    @Override
    public String toString() {
        return name().toLowerCase();
    }
    
    public String getDescription() {
    	return description;
    }
}
