package ma.enset.bankaccountservice;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.enums.AccountType;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void shouldAddUpdateAndDeleteAccount() {
        BankAccountResponseDTO created = accountService.addAccount(
                new BankAccountRequestDTO(3000.0, "MAD", AccountType.CURRENT_ACCOUNT));
        assertThat(created.getId()).isNotBlank();
        assertThat(created.getCreatedAt()).isNotNull();
        assertThat(created.getBalance()).isEqualTo(3000.0);

        BankAccountResponseDTO updated = accountService.updateAccount(created.getId(),
                BankAccountRequestDTO.builder().balance(4500.0).build());
        assertThat(updated.getBalance()).isEqualTo(4500.0);
        assertThat(updated.getCurrency()).isEqualTo("MAD");
        assertThat(updated.getType()).isEqualTo(AccountType.CURRENT_ACCOUNT);

        accountService.deleteAccount(created.getId());
        assertThatThrownBy(() -> accountService.accountById(created.getId()))
                .isInstanceOf(AccountNotFoundException.class);
    }
}
