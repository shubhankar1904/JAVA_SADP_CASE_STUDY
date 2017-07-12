package com.sapient.dbHandler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapient.entities.Fill;

public interface FillRepository extends CrudRepository<Fill, Long> {

	@Transactional
	@Query("select u from Fill u where u.symbol=?1")
	List<Fill> filterSymbol(String symbol);

	@Transactional
	@Query("select u from Fill u where u.side=?1")
	List<Fill> filterSide(String side);

	@Transactional
	@Query("select u from Fill u where u.side=?1 AND u.symbol=?2")

	List<Fill> filterBoth(String side, String symbol);

	@Transactional
	@Query("select u from Fill u where u.blockId=?1")

	List<Fill> filterblockID(double blockId);

	@Transactional
	@Query("select u from Fill u where u.executionId=?1")

	List<Fill> filterexecID(long execId);
	
	
	@Query("from Fill")
	List<Fill> listAll();

}
