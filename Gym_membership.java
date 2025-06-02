/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.samissua.gym_membership;

import java.util.ArrayList;

/**
 *
 * @author CltControl
 */
public class Gym_membership {

    private ArrayList<Membership> membership_list = new ArrayList<>();
    private ArrayList<Feature> feature_list = new ArrayList<>();

    public static void main(String[] args) {

    }

    public void setMemberships() {
        Membership basic = new Membership("Basic", 60.0f);
        Membership premium = new Membership("Premium", 90.0f);
        Membership family = new Membership("Family", 120.0f);

        membership_list.add(basic);
        membership_list.add(premium);
        membership_list.add(family);
    }

    public void setFeatures() {
        Feature training_session = new Feature("Training Sessions", 60.0f);
        Feature group_classes = new Feature("Group Classes", 90.0f);

        feature_list.add(training_session);
        feature_list.add(group_classes);
    }

}
