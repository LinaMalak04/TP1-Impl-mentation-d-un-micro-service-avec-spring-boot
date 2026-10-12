package ma.enset.bankaccountservice;

import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.enums.AccountType;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        bankAccountRepository.deleteAll();
        bankAccountRepository.save(new BankAccount("acc-1", new Date(), 5000.0, "MAD", AccountType.CURRENT_ACCOUNT));
        bankAccountRepository.save(new BankAccount("acc-2", new Date(), 12000.0, "MAD", AccountType.SAVING_ACCOUNT));
        bankAccountRepository.save(new BankAccount("acc-3", new Date(), 800.0, "EUR", AccountType.SAVING_ACCOUNT));
    }

    @Test
    void shouldFindAllAccounts() {
        assertThat(bankAccountRepository.findAll()).hasSize(3);
    }

    @Test
    void shouldFindAccountById() {
        Optional<BankAccount> account = bankAccountRepository.findById("acc-1");
        assertThat(account).isPresent();
        assertThat(account.get().getBalance()).isEqualTo(5000.0);
        assertThat(account.get().getType()).isEqualTo(AccountType.CURRENT_ACCOUNT);
    }

    @Test
    void shouldFindAccountsByType() {
        List<BankAccount> savingAccounts = bankAccountRepository.findByType(AccountType.SAVING_ACCOUNT);
        assertThat(savingAccounts).extracting(BankAccount::getId).containsExactlyInAnyOrder("acc-2", "acc-3");
    }

    @Test
    void shouldUpdateAndDeleteAccount() {
        BankAccount account = bankAccountRepository.findById("acc-1").orElseThrow();
        account.setBalance(7500.0);
        bankAccountRepository.save(account);
        assertThat(bankAccountRepository.findById("acc-1").orElseThrow().getBalance()).isEqualTo(7500.0);

        bankAccountRepository.deleteById("acc-1");
        assertThat(bankAccountRepository.existsById("acc-1")).isFalse();
    }
}
