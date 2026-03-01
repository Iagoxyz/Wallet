package tech.backend.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
