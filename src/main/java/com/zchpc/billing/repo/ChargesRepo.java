package com.zchpc.billing.repo;

import com.zchpc.billing.model.Charges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargesRepo extends JpaRepository<Charges, Long> {
}
