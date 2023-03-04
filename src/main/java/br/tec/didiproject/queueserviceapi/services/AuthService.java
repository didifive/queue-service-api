package br.tec.didiproject.queueserviceapi.services;

import br.tec.didiproject.queueserviceapi.dtos.request.AuthDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RefreshTokenResponseDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.TokenResponseDTO;
import br.tec.didiproject.queueserviceapi.entities.RefreshToken;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import br.tec.didiproject.queueserviceapi.exceptions.EntityNotFoundException;
import br.tec.didiproject.queueserviceapi.exceptions.TokenRefreshException;
import br.tec.didiproject.queueserviceapi.repositories.RefreshTokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.REFRESH_TOKEN_EXPIRED;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.REFRESH_TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioService usuarioService;
    @Value("${queue-service-api.jwt.refresh-expiration}")
    private String refreshExpiration;
    @Value("${queue-service-api.jwt.secret}")
    private String secret;
    @Value("${queue-service-api.jwt.expiration}")
    private String expiration;
    @Value("${queue-service-api.jwt.issuer}")
    private String issuer;

    public TokenResponseDTO autenticar(AuthDTO authDto) throws AuthenticationException {
        Authentication authenticate = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        return gerarToken(authenticate);
    }

    private Algorithm criarAlgoritimo() {
        return Algorithm.HMAC256(secret);
    }

    private TokenResponseDTO gerarToken(Authentication authenticate) {
        Usuario principal = (Usuario) authenticate.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = expirationDate(hoje);

        String token = JWT.create().withIssuer(issuer).withExpiresAt(dataExpiracao).withSubject(principal.getId().toString())
                .sign(this.criarAlgoritimo());

        Optional<RefreshToken> existingRefreshToken = refreshTokenRepository.findByUsuario(principal);
        if (existingRefreshToken.isPresent())
            deleteRefreshTokenByUserId(principal);

        RefreshToken refreshToken = gerarRefreshToken(principal);

        return new TokenResponseDTO(token
                , refreshToken.getId().toString()
                , principal.getId().toString()
                , principal.getNomeUsuario());
    }

    public RefreshTokenResponseDTO gerarToken(UUID usuarioId, UUID refreshTokenId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Date hoje = new Date();
        Date dataExpiracao = expirationDate(hoje);

        String newToken = JWT.create().withIssuer(issuer).withExpiresAt(dataExpiracao).withSubject(usuario.getId().toString())
                .sign(this.criarAlgoritimo());

        RefreshToken refreshToken = findRefreshToken(refreshTokenId);
        verificarExpiracaoRefreshToken(refreshToken);
        deleteRefreshTokenByUserId(usuario);

        RefreshToken newRefreshToken = gerarRefreshToken(usuario);

        return new RefreshTokenResponseDTO(newToken, newRefreshToken.getId().toString());
    }

    protected Date expirationDate(Date hoje) {
        return new Date(hoje.getTime() + Long.parseLong(expiration));
    }

    public boolean verificaToken(String token) {
        try {
            if (token == null)
                return false;

            JWT.require(this.criarAlgoritimo()).withIssuer(issuer).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

    public UUID retornarUsuarioId(String token) {
        String subject = JWT.require(this.criarAlgoritimo()).withIssuer(issuer).build().verify(token).getSubject();
        return UUID.fromString(subject);

    }

    public RefreshToken findRefreshToken(UUID refreshTokenId) {
        return refreshTokenRepository.findById(refreshTokenId).orElseThrow(
                () -> new EntityNotFoundException(
                        REFRESH_TOKEN_NOT_FOUND.params(refreshTokenId.toString()).getMessage()));
    }

    public RefreshToken gerarRefreshToken(Usuario usuario) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUsuario(usuario);
        Date hoje = new Date();
        refreshToken.setExpiryDate(new Date(hoje.getTime() + Long.parseLong(refreshExpiration)));

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public void verificarExpiracaoRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(new Date()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(REFRESH_TOKEN_EXPIRED.getMessage());
        }
    }

    @Transactional
    public void deleteRefreshTokenByUserId(Usuario usuario) {
        refreshTokenRepository.deleteByUsuario(usuario);
    }

}
