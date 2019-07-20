package com.example.userservice;

import net.ricecode.similarity.JaroStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPasswordService {

    private final UserRepository userRepository;
    private final PasswordRuleValidator passwordRuleValidator;

    public UserPasswordService(UserRepository userRepository, PasswordRuleValidator passwordRuleValidator) {

        this.userRepository = userRepository;
        this.passwordRuleValidator = passwordRuleValidator;
    }

    public boolean updatePassword(UserData userData, Long id) {

        if (canChangePassword(userData.getPassword(), id)) {
            userRepository.findById(id)
                    .map(user -> {
                        user.setUsername(userData.getUsername());
                        user.setPassword(userData.getPassword());
                        return userRepository.save(user);
                    })
                    .orElseGet(() -> {
                        userData.setId(id);
                        return userRepository.save(userData);
                    });
            return true;
        } else {
            return false;
        }
    }

    private boolean canChangePassword(String password, Long id) {
        SimilarityStrategy strategy = new JaroStrategy();
        Optional<UserData> user = userRepository.findById(id);
        String oldPassword = user.get().getPassword();
        String newPassword = password;
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        double score = service.score(oldPassword,newPassword);
        if(score>0.8)
        {
            return false;
        }

        return passwordRuleValidator.isValid(password);
    }
}

