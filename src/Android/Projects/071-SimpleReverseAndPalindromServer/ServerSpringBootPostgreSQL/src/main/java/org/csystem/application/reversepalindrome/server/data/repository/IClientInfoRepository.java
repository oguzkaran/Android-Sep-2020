package org.csystem.application.reversepalindrome.server.data.repository;

import org.csystem.application.reversepalindrome.server.data.entity.ClientInfo;
import org.springframework.data.repository.CrudRepository;

public interface IClientInfoRepository extends CrudRepository<ClientInfo, Long> {

}
