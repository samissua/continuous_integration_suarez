/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samissua.gymsystem;

import java.util.List;

/**
 *
 * @author samissua
 */
public class Feature implements Service{
    private String name;
    private int cost;

    public Feature(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    
    @Override
    public String getName() {
        return name; 
    }

    @Override
    public void setName(String name) {
        this.name=name; 
    }

    @Override
    public int getPrice() {
        return cost; 
    }

    @Override
    public void setPrice(int price) {
        this.cost=price; 
    }
    
    
}
