package br.tec.didiproject.queueserviceapi.controllers;

import br.tec.didiproject.queueserviceapi.controllers.docs.AuthControllerDocs;
import br.tec.didiproject.queueserviceapi.dtos.request.AuthDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RefreshTokenResponseDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.TokenResponseDTO;
import br.tec.didiproject.queueserviceapi.exceptions.InvalidCredetialsException;
import br.tec.didiproject.queueserviceapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_AUTH;
import static br.tec.didiproject.queueserviceapi.exceptions.BaseErrorMessage.INVALID_USERNAME_PASSWORD;
import static br.tec.didiproject.queueserviceapi.utils.UUIDValidator.validateUUIDPattern;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_AUTH)
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponseDTO> authenticate(@RequestBody AuthDTO authDto) {
        try {
            return ResponseEntity.ok(authService.autenticar(authDto));
        } catch (AuthenticationException e) {
            throw new InvalidCredetialsException(
                    INVALID_USERNAME_PASSWORD.getMessage()
            );
        }
    }

    @PostMapping("/refresh/{usuarioId}/{refreshToken}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(
            @PathVariable String usuarioId
            , @PathVariable String refreshToken) {

        validateUUIDPattern(usuarioId);
        validateUUIDPattern(refreshToken);
        return ResponseEntity.ok(
                authService.gerarToken(
                        UUID.fromString(usuarioId)
                        , UUID.fromString(refreshToken)
                )
        );
    }

    @DeleteMapping("/invalidate-refresh/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> invalidaRefreshToken(
            @PathVariable String usuarioId) {
        validateUUIDPattern(usuarioId);
        authService.deleteRefreshTokenByUserId(UUID.fromString(usuarioId));
        return ResponseEntity.noContent().build();
    }

}
