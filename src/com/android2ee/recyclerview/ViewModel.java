package com.android2ee.recyclerview;

public class ViewModel {

	 private String primaryText;
    private String secondText;
    private int icon;

    public ViewModel(String primaryText, String secondText, int icon) {
        this.primaryText = primaryText;
        this.secondText = secondText;
        this.icon = icon;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String text) {
        this.primaryText = text;
    }
    
    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String text) {
        this.secondText = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
