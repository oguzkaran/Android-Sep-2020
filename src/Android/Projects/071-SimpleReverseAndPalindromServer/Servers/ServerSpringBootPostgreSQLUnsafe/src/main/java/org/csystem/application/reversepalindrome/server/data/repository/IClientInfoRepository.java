package org.csystem.application.reversepalindrome.server.data.repository;

import org.csystem.application.reversepalindrome.server.data.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientInfoRepository extends CrudRepository<Client, Long> {

}
