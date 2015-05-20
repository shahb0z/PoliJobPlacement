package it.polito.mobile.polijobplacement.Profile;


public class EntryItem implements Item{

	public final String title;
	public final String subtitle;
    public final int entryType;
	public EntryItem(String title, String subtitle, int s) {
		this.title = title;
		this.subtitle = subtitle;
        this.entryType = s;
	}
	
	@Override
	public int getType() {
		return 1;
	}

    @Override
    public boolean hasChild() {
        return true;
    }


}
