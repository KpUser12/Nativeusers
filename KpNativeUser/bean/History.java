
package com.kp.admin.bean;


public class History {

    private String userId;
    private String action;
    private String actionOn;
    private String actionTakenBy;

    /**
     * No args constructor for use in serialization
     * 
     */
    public History() {
    }

    /**
     * 
     * @param actionTakenBy
     * @param action
     * @param userId
     * @param actionOn
     */
    public History(String userId, String action, String actionOn, String actionTakenBy) {
        super();
        this.userId = userId;
        this.action = action;
        this.actionOn = actionOn;
        this.actionTakenBy = actionTakenBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionOn() {
        return actionOn;
    }

    public void setActionOn(String actionOn) {
        this.actionOn = actionOn;
    }

    public String getActionTakenBy() {
        return actionTakenBy;
    }

    public void setActionTakenBy(String actionTakenBy) {
        this.actionTakenBy = actionTakenBy;
    }

}
