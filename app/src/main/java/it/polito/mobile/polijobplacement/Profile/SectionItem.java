package it.polito.mobile.polijobplacement.Profile;

public class SectionItem implements Item{

	private final String title;
	
	public SectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	@Override
	public int getType() {
		return 0;
	}

	@Override
	public boolean hasChild() {
		return false;
	}

}
