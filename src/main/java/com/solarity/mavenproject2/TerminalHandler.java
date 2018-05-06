/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solarity.mavenproject2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author redlo
 */
public class TerminalHandler {

    public static Map<Account, Set<String>> getDailyReport(List<Account> accounts, Map<String, String> termRelAccounts) {
        Map<Account, Set<String>> result = null;
        if (accounts != null && termRelAccounts != null) {
            result = new HashMap<>();
            for(Account account : accounts){
                for(String number : account.getNumbers()){
                    if(termRelAccounts.containsKey(number)){
                        result.putIfAbsent(account, new HashSet<>());
                        Set<String> set = result.get(account);
                        result.merge(account, set, set.add(number));
                    }
                }
            }
//            result = accounts.stream()
//                    .flatMap(account -> account.getNumbers().stream())
//                    .filter(number -> termRelAccounts.containsKey(number))
//                    .collect(Collectors.toMap(account, num -> num));
                    
        }
        return result;
    }

}
