package com.sapient.dbHandler;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sapient.moneytree.executionTrader.domain.Block;

public interface BlockRepository extends JpaRepository<Block, Long> {

}
