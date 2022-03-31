package me.dyatkokg.balancecachingapi.repository;

import me.dyatkokg.balancecachingapi.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    List<Balance> findAllByBalanceLessThan(double balance);
}
