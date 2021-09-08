package ua.bykov.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class UserInfoContainer {
    private ConcurrentHashMap<String, String> userInfo = new ConcurrentHashMap<>();
}
