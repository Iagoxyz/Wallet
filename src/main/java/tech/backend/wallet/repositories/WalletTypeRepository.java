package tech.backend.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.wallet.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
