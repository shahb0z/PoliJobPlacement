package it.polito.mobile.polijobplacement.Profile;

/**
 * Created by shahboz on 17/05/2015.
 */
public class ButtonItem implements Item {
    public final String title;
    public final int buttonType;
    public ButtonItem(String title, int buttonType){
        this.title = title;
        this.buttonType = buttonType;
    }
    @Override
    public int getType() {
        return 2;
    }

    @Override
    public boolean hasChild() {
        return true;
    }

}
