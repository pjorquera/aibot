package com.okode.aibot.repository;

import com.okode.aibot.model.Policy;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PolicyRepository extends CrudRepository<Policy, String> {

    List<Policy> findByContractDateBetween(Date contractDateStart, Date contractDateEnd);

}
