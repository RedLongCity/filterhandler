/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solarity.mavenproject2;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author redlo
 */
public class FilterDescription {
    
    private String name;
    
    private List<FilterOption> options;

    public List<FilterOption> getOptions() {
        return options;
    }

    public void setOptions(List<FilterOption> options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.options);
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
        final FilterDescription other = (FilterDescription) obj;
        if (!Objects.equals(this.options, other.options)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilterDescription{" + "options=" + options + '}';
    }
    
}
