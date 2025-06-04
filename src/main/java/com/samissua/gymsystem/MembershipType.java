/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samissua.gymsystem;

import java.util.ArrayList;

/**
 *
 * @author samissua
 */
public enum MembershipType {
    BASIC(50),
    PREMIUM(100),
    FAMILY(150);

    private final int baseCost;

    MembershipType(int cost) {
        this.baseCost = cost;
    }

    public int getBaseCost() {
        return baseCost;
    }
}
