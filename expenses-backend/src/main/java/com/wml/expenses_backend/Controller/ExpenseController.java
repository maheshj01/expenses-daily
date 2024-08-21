package com.wml.expenses_backend.Controller;

import com.wml.expenses_backend.dto.ExpenseDTO;
import com.wml.expenses_backend.Entity.Expense;
import com.wml.expenses_backend.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Expense> createExpense(@PathVariable Long userId, @RequestBody ExpenseDTO expenseDTO) {
        Expense expense = expenseService.createExpense(userId, expenseDTO);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUser(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUser(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{userId}/date-range")
    public ResponseEntity<List<Expense>> getExpensesByUserAndDateRange(
            @PathVariable Long userId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            List<Expense> expenses = expenseService.getExpensesByUserAndDateRange(userId, start, end);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}/label/{labelId}")
    public ResponseEntity<List<Expense>> getExpensesByLabel(@PathVariable Long userId, @PathVariable Long labelId) {
        List<Expense> expenses = expenseService.getExpensesByLabel(userId, labelId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
