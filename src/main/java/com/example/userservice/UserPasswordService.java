package com.example.userservice;

import net.ricecode.similarity.JaroStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {

    private final UserRepository userRepository;
    private final PasswordRuleValidator passwordRuleValidator;

    public UserPasswordService(UserRepository userRepository, PasswordRuleValidator passwordRuleValidator) {

        this.userRepository = userRepository;
        this.passwordRuleValidator = passwordRuleValidator;
    }

    private boolean isPasswordDifferent(String oldPassword, String newPassword) {
        SimilarityStrategy strategy = new JaroStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        double score = service.score(oldPassword, newPassword);
        if (score > 0.8) {
            return false;
        } else
            return true;
    }

    private boolean canChangePassword(String oldPassword, String newPassword) {
        boolean isSimilar = isPasswordDifferent(oldPassword, newPassword);
        boolean isPasswordValid = passwordRuleValidator.isValid(newPassword);
        return isSimilar && isPasswordValid;
    }

    public boolean changePassword(String oldPassword, String newPassword, Long id) {
        if (canChangePassword(oldPassword, newPassword)) {
            userRepository.findById(id).map(user -> {
                user.setCurrentPassword(newPassword);
                user.setNewPassword(null);
                return userRepository.save(user);
            }).orElseGet(() -> {
                throw new RuntimeException("User Does not exist");
            });
            return true;
        } else
            return false;
    }
}

