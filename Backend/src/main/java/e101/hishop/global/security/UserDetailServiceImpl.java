package e101.hishop.global.security;

import e101.hishop.domain.entity.User;
import e101.hishop.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
@Configuration
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserJPARepository userJPARepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.info("==== loadingUserByUsername ===== {}", loginId);
        User user = userJPARepository.findByLoginId(loginId).orElseThrow(() -> new EntityNotFoundException("Employee not found with id:" + loginId));
        log.info("==== user found in the database ====: {}", loginId);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        //spring security User class
        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), authorities);
    }
}
