package com.sapient.dbHandler;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.entities.Fill;
import com.sapient.entities.Security;

public interface SecurityRepository extends JpaRepository<Security, Long> {

	@Modifying
	@Transactional
	@Query("Update Security u set u.spread=?1," + "	u. execNo=?2	," + " u.interval=?3	," + " u.prob=?4 "
			+ "where u.secSymbol=?5")
	public void UpdateSecurity(double spread, int execNo, double interval, double prob, String symbol);


	@Transactional
	@Query("select u from Security u where u.secSymbol=?1")
	Security filterSymbol(String symbol);



	@Modifying
	@Transactional
	@Query("Update Security u set u.lastPrice=?1 " + "where u.secSymbol=?2")
	public void UpdateLastPrice(double lastPrice,String Symbol);

	

	@Query("from Security ORDER BY id DESC")
	List<Security> listAll();
	
	/*@Transactional
	@Query("Update Security u  set u.Last_Price=?1	from  where u.secSymbol=?2")
	public void updateNewPrice(double newMarketPrice,String symbol);*/


	
	
	
}
