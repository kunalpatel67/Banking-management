package com.example.demo.repository;

import com.example.demo.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatementRepo extends JpaRepository<Statement,Integer> {
    @NativeQuery @Query(value = "select * from statement where account_no=?1", nativeQuery = true)
    List<Statement> findByAccNo(int accNo);
}
