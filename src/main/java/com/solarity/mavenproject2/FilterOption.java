/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solarity.mavenproject2;

import java.util.Objects;

/**
 *
 * @author redlo
 */
public class FilterOption {
    
    private Object value;

    public Object getValue() {
        return value;
    }

    public FilterOption(Object value) {
        this.value = value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }

    public FilterOption() {
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FilterOption other = (FilterOption) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilterOption{" + "value=" + value + '}';
    }
    
    
    
}
