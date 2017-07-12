package com.sapient.moneytree.portfolioManager.DatabaseInterfaces;

import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_2.ast.conditions.orderByOnlyOnIdentifiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.moneytree.portfolioManager.domain.OrderCommunicator;
import com.sapient.moneytree.portfolioManager.domain.Orders;

public interface OrderCommunicatorDatabaseInterface extends JpaRepository<OrderCommunicator, Integer> {

}
