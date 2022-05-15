package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.LoggedInUser;

public interface ILoggedInUserService {
    public boolean isRepresentativeOfTheAssociation(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code) throws Exception;

    public LoggedInUser getByCode(String code) throws Exception;

    LoggedInUser saveLoggedInUser(LoggedInUser loggedInUser);
}
