package com.nemo.footballassociation.Service;

import com.nemo.footballassociation.Contracts.Interfaces.Repository.ILoggedInUserRepository;
import com.nemo.footballassociation.Contracts.Interfaces.Service.ILoggedInUserService;
import com.nemo.footballassociation.Contracts.Modules.DbModels.LoggedInUser;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserService implements ILoggedInUserService {
    private ILoggedInUserRepository loggedInUserRepository;

    public LoggedInUserService(ILoggedInUserRepository loggedInUserRepository) {
        this.loggedInUserRepository = loggedInUserRepository;
    }

    @Override
    public boolean isRepresentativeOfTheAssociation(String code) {
        if (!loggedInUserRepository.existsByCode(code)) return false;

        //TODO: add logic!! use expiration date!
        return true;
    }

    @Override
    public boolean existsByCode(String code) {
        return loggedInUserRepository.existsByCode(code);
    }

    @Override
    public void deleteByCode(String code) throws Exception {
        loggedInUserRepository.findByCode(code).orElseThrow(() ->
                new Exception(""));
        loggedInUserRepository.deleteByCode(code);
    }

    @Override
    public LoggedInUser getByCode(String code) throws Exception {
        return loggedInUserRepository.findByCode(code).orElseThrow(() ->
                new Exception(""));
    }

    @Override
    public LoggedInUser saveLoggedInUser(LoggedInUser loggedInUser) {
        loggedInUser.setId(loggedInUserRepository.findMaxId() == null ? 0 : loggedInUserRepository.findMaxId() + 1);
        return loggedInUserRepository.save(loggedInUser);
    }
}
