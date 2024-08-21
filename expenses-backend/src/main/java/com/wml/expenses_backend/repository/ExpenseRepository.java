package com.wml.expenses_backend.repository;

import com.wml.expenses_backend.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserUserId(Long userId);
    List<Expense> findByUserUserIdAndDateBetween(Long userId, Date startDate, Date endDate);
    List<Expense> findByUserUserIdAndLabelLabelId(Long userId, Long labelId);
}
