/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solarity.mavenproject2;

import java.util.List;

/**
 *
 * @author redlo
 */
public class Account {
    
    private String dailyEmail;
    
    private String monthEmail;
    
    private List<String> numbers;

    public String getDailyEmail() {
        return dailyEmail;
    }

    public void setDailyEmail(String dailyEmail) {
        this.dailyEmail = dailyEmail;
    }

    public String getMonthEmail() {
        return monthEmail;
    }

    public void setMonthEmail(String monthEmail) {
        this.monthEmail = monthEmail;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public String toString() {
        return "Acccount{" + "dailyEmail=" + dailyEmail + ", monthEmail=" + monthEmail + '}';
    }
    
    
    
}
