package hu.Szebasztian.basicBillManagementApp.services;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 2;

    private final Map<String, Integer> attemptsCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String key) {
        attemptsCache.remove(key);
    }

    public void loginFailed(String key) {
        int attempts = attemptsCache.getOrDefault(key, 0);
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isCaptchaRequired(String key) {
        int attempts = attemptsCache.getOrDefault(key, 0);
        return attempts >= MAX_ATTEMPTS;
    }

}
