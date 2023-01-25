package e101.hishop.service;

import e101.hishop.domain.entity.User;
import org.springframework.stereotype.Service;

public interface AuthService {
    Long signUp(User user);
}
