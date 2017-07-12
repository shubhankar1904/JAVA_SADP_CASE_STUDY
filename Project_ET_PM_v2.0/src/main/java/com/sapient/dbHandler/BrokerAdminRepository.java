package com.sapient.dbHandler;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.entities.BrokerAdmin;

public interface BrokerAdminRepository extends JpaRepository<BrokerAdmin, Long> {

}
