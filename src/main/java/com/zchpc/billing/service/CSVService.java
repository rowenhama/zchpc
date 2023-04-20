package com.zchpc.billing.service;

import com.zchpc.billing.helper.CSVHelper;
import com.zchpc.billing.model.Accounts;
import com.zchpc.billing.repo.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    AccountsRepo accountsRepo;

    public Boolean save(MultipartFile file) {
        try {
            List<Accounts> accounts = CSVHelper.csvAccounts(file.getInputStream());
            accountsRepo.saveAll(accounts);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        return true;
    }

    public ByteArrayInputStream load() {
        List<Accounts> tutorials = accountsRepo.findAll();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
        return in;
    }

    public List<Accounts> getAllTutorials() {
        return accountsRepo.findAll();
    }
}
