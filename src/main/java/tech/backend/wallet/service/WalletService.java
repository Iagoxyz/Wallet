package tech.backend.wallet.service;

import org.springframework.stereotype.Service;
import tech.backend.wallet.controller.dto.CreateWalletDto;
import tech.backend.wallet.entity.Wallet;
import tech.backend.wallet.exception.WalletDataAlreadyExistsException;
import tech.backend.wallet.repositories.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
