package application.capstone.security;

import application.capstone.entities.User;
import application.capstone.exceptions.BadRequestException;
import application.capstone.exceptions.UnauthorizedException;
import application.capstone.repositories.UserRepository;
import application.capstone.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserRepository usersRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
        } else {
            String token = authHeader.substring(7);
            jwtTools.verifyToken(token);

            String id = jwtTools.extractIdFromToken(token);
            User currentUser = usersRepo.findById(UUID.fromString(id)).orElseThrow(() -> new BadRequestException("utente non trovato rifare il token"));


            Authentication authentication  = new UsernamePasswordAuthenticationToken(currentUser , null ,  currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request , response);

        }



    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        return new AntPathMatcher().match("/public/**", request.getServletPath());
    }
}
