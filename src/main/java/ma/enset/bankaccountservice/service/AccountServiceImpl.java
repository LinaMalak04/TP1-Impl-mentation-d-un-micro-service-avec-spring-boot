package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountservice.entities.BankAccount;
import ma.enset.bankaccountservice.exceptions.AccountNotFoundException;
import ma.enset.bankaccountservice.mappers.AccountMapper;
import ma.enset.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<BankAccountResponseDTO> accounts() {
        return bankAccountRepository.findAll().stream().map(accountMapper::fromBankAccount).toList();
    }

    @Override
    public BankAccountResponseDTO accountById(String id) {
        return accountMapper.fromBankAccount(findAccount(id));
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return accountMapper.fromBankAccount(bankAccountRepository.save(bankAccount));
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = findAccount(id);
        if (bankAccountDTO.getBalance() != null) bankAccount.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getCurrency() != null) bankAccount.setCurrency(bankAccountDTO.getCurrency());
        if (bankAccountDTO.getType() != null) bankAccount.setType(bankAccountDTO.getType());
        return accountMapper.fromBankAccount(bankAccountRepository.save(bankAccount));
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.delete(findAccount(id));
    }

    private BankAccount findAccount(String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }
}
