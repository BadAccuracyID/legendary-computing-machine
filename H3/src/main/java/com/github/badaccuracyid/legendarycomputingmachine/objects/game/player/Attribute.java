package com.github.badaccuracyid.legendarycomputingmachine.objects.game.player;

public class Attribute {

    private final String att1Name, att2Name;
    private int att1Value, att2Value;

    public Attribute(String att1Name, String att2Name, int att1Value, int att2Value) {
        this.att1Name = att1Name;
        this.att2Name = att2Name;
        this.att1Value = att1Value;
        this.att2Value = att2Value;
    }

    public String getAtt1Name() {
        return att1Name;
    }

    public String getAtt2Name() {
        return att2Name;
    }

    public int getAtt1Value() {
        return att1Value;
    }

    public int getAtt2Value() {
        return att2Value;
    }

    public void setAtt1Value(int att1Value) {
        this.att1Value = att1Value;
    }

    public void setAtt2Value(int att2Value) {
        this.att2Value = att2Value;
    }
}
