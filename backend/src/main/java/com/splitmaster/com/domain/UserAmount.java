//package com.splitmaster.com.domain;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "user_amount_table")
//public class UserAmount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long userId;
//    private BigDecimal amount;
//    private String transactionDescription;
//    private LocalDateTime transactionDateTime;
//    private Long sendToUserId;
//    private Long receiveFromUserId;
//    private Long userGroupId;
//    private Long paidBy;
//    private boolean isActive;
//}
