package ma.enset.bankaccountservice.repositories;

import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByType(AccountType type);
}
