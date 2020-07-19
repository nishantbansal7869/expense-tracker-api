package com.expensetrackerapi.expensetracker.resources;

import com.expensetrackerapi.expensetracker.domain.Transaction;
import com.expensetrackerapi.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction>  getTransactionById(HttpServletRequest httpRequest,
                                                           @PathVariable("transactionId") Integer transactionId,
                                                           @PathVariable("categoryId") Integer categoryId){
        int userId = (Integer) httpRequest.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest httpRequest,
                                                                @PathVariable("categoryId") Integer categoryId){
        int userId = (Integer) httpRequest.getAttribute("userId");
        List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest httpRequest,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction){
        int userId = (Integer) httpRequest.getAttribute("userId");
        transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest httpRequest,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap){
        int userId = (Integer) httpRequest.getAttribute("userId");
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = transactionMap.get("note").toString();
        Long transactionDate = Long.valueOf(transactionMap.get("transactionDate").toString());
        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

}
