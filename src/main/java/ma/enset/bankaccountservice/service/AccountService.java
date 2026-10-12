package ma.enset.bankaccountservice.service;

import ma.enset.bankaccountservice.dto.BankAccountRequestDTO;
import ma.enset.bankaccountservice.dto.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<BankAccountResponseDTO> accounts();

    BankAccountResponseDTO accountById(String id);

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);

    void deleteAccount(String id);
}
