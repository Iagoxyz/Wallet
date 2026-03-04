package tech.backend.wallet.service;

import feign.FeignException;
import org.springframework.stereotype.Service;
import tech.backend.wallet.client.AuthorizationClient;
import tech.backend.wallet.controller.dto.TransferDto;
import tech.backend.wallet.exception.WalletException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {
        try {
            var response = authorizationClient.isAuthorized();

            // Se a API responder corretamente (200)
            return response.getBody() != null && response.getBody().authorized();

        } catch (FeignException.Forbidden e) {
            // 403 → regra de negócio: não autorizado
            return false;

        } catch (FeignException e) {
            // Qualquer outro erro externo (500, timeout, etc)
            throw new WalletException();
        }
    }
}