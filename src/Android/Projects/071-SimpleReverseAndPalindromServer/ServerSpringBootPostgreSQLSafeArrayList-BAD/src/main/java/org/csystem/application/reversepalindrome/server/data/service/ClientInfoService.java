package org.csystem.application.reversepalindrome.server.data.service;

import org.csystem.application.reversepalindrome.server.data.entity.Client;
import org.csystem.application.reversepalindrome.server.data.repository.IClientInfoRepository;
import org.csystem.util.data.service.DataServiceException;
import org.springframework.stereotype.Service;

@Service
public class ClientInfoService {
    private final IClientInfoRepository m_clientInfoRepository;

    public ClientInfoService(IClientInfoRepository clientInfoRepository)
    {
        m_clientInfoRepository = clientInfoRepository;
    }

    public Client save(Client client)
    {
        try {
            return m_clientInfoRepository.save(client);
        }
        catch (Throwable ex) {
            throw new DataServiceException("save", ex);
        }
    }
}
