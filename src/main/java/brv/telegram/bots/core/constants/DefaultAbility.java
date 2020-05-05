package brv.telegram.bots.core.constants;

public enum DefaultAbility {

	BAN,
	UNBAN,
	PROMOTE,
	DEMOTE;
	
    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
