package e101.hishop.global.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import e101.hishop.global.common.CommonException;
import e101.hishop.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserJPARepository userJPARepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("====attempting Authentication........");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("username is : {}", username);
        log.info("password is : {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    //Handler 사용?
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //Security User Class
        User user = (User)authentication.getPrincipal();
        //TODO 추후 key 따로저장
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        // repository에서 id검색
        e101.hishop.domain.entity.User jpaUser = userJPARepository.findByLoginId(user.getUsername())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority authority : user.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        //TODO accesstoken 시간변경
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", authorities)
                .withClaim("user-id",jpaUser.getId() )
                // .withClaim("id", id)
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30000 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("access-token", accessToken);
        response.setHeader("refresh-token", refreshToken);
        Map<String, String> tokens = new HashMap<>();
        //TODO header로 반환

//        tokens.put("access-token", accessToken);
//        tokens.put("refresh-token", refreshToken);
//        response.setContentType(APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
