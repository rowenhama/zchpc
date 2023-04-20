package com.zchpc.billing.repo;

import com.zchpc.billing.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {
}