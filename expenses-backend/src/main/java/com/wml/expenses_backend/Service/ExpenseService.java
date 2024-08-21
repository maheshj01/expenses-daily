package com.wml.expenses_backend.Service;

import com.wml.expenses_backend.Entity.Expense;

import com.wml.expenses_backend.dto.ExpenseDTO;
import com.wml.expenses_backend.Entity.Expense;
import com.wml.expenses_backend.Entity.Label;
import com.wml.expenses_backend.Entity.User;
import com.wml.expenses_backend.repository.ExpenseRepository;
import com.wml.expenses_backend.repository.LabelRepository;
import com.wml.expenses_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabelRepository labelRepository;

    public Expense createExpense(Long userId, ExpenseDTO expenseDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Label> labelOptional = labelRepository.findById(expenseDTO.getLabelId());

        if (userOptional.isPresent() && labelOptional.isPresent()) {
            Expense expense = new Expense();
            expense.setUser(userOptional.get());
            expense.setLabel(labelOptional.get());
            expense.setDate(expenseDTO.getDate());
            expense.setAmount(expenseDTO.getAmount());
            expense.setCurrency(expenseDTO.getCurrency());
            expense.setDescription(expenseDTO.getDescription());
            return expenseRepository.save(expense);
        } else {
            throw new RuntimeException("User or Label not found");
        }
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserUserId(userId);
    }

    public List<Expense> getExpensesByUserAndDateRange(Long userId, Date startDate, Date endDate) {
        return expenseRepository.findByUserUserIdAndDateBetween(userId, startDate, endDate);
    }

    public List<Expense> getExpensesByLabel(Long userId, Long labelId) {
        return expenseRepository.findByUserUserIdAndLabelLabelId(userId, labelId);
    }
}
