package com.nemo.footballassociation.Contracts.Interfaces.Service;

import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;

import java.util.List;

public interface IRefereeService {
    Referee saveReferee(Referee referee);
    List<Referee> getAllReferee();
    Referee getRefereeById(int id) throws Exception;
    List<Referee> getRefereeByUserName(String userName) throws Exception;
    boolean existsByUserName(String userName) throws Exception;
    Referee updateReferee(Referee referee, int id) throws Exception;
    void deleteReferee(int id) throws Exception;
}
