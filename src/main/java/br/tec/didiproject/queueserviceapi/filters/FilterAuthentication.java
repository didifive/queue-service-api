package br.tec.didiproject.queueserviceapi.filters;

import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.services.AuthService;
import br.tec.didiproject.queueserviceapi.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class FilterAuthentication extends OncePerRequestFilter {

    private final AuthService authService;

    private final UsuarioService usuarioService;

    public FilterAuthentication(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @SuppressWarnings("NullableProblems") HttpServletResponse response, @SuppressWarnings("NullableProblems") FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (authService.verificaToken(token)) {
            UUID usuarioId = authService.retornarUsuarioId(token);
            Usuario usuario = usuarioService.findById(usuarioId);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }

}
