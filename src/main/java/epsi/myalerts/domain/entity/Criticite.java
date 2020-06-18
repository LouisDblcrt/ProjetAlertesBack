package epsi.myalerts.domain.entity;

public enum Criticite {

	Urgent(5),
	Forte(4),
	Moyenne(3),
	Faible(2),
	Information(1);
	
	private final int criticite;
	 Criticite(int criticite) {
		this.criticite=criticite;
	}
}
