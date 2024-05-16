package com.evilapp.fire.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class BlackListService {
    private final Set<String> blacklist = new HashSet<>();

    public boolean addToBlacklist(String token) {
        return blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}