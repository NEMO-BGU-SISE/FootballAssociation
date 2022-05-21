package com.nemo.footballassociation.Service;


import com.nemo.footballassociation.Contracts.Interfaces.Repository.IRefereeRepository;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import com.nemo.footballassociation.Contracts.Interfaces.Service.IRefereeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeService implements IRefereeService {

    private IRefereeRepository refereeRepository;

    public RefereeService(IRefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    @Override
    public Referee saveReferee(Referee referee) {
        referee.setId(refereeRepository.findMaxId() == null ? 0 : refereeRepository.findMaxId() + 1);
        return refereeRepository.save(referee);
    }

    @Override
    public List<Referee> getAllReferee() {
        return refereeRepository.findAll();
    }

    @Override
    public Referee getRefereeById(int id) throws Exception {
//		Optional<Referee> referee = refereeRepository.findById(id);
//		if(referee.isPresent()) {
//			return referee.get();
//		}else {
//			throw new ResourceNotFoundException("Referee", "Id", id);
//		}
        return refereeRepository.findById(id).orElseThrow(() ->
                new Exception(""));

    }

    @Override
    public Referee getRefereeByUserName(String userName) {
        return refereeRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return refereeRepository.existsByUserName(userName);
    }

    @Override
    public Referee updateReferee(Referee referee, int id) throws Exception {

        // we need to check whether referee with given id is exist in DB or not
        Referee existingReferee = refereeRepository.findById(id).orElseThrow(
                () -> new Exception("Referee"));

        existingReferee.setName(referee.getName());
        existingReferee.setUserName(referee.getUserName());
        existingReferee.setPassword(referee.getPassword());
        // save existing referee to DB
        refereeRepository.save(existingReferee);
        return existingReferee;
    }

    @Override
    public void deleteReferee(int id) throws Exception {

        // check whether a referee exist in a DB or not
        refereeRepository.findById(id).orElseThrow(() ->
                new Exception("Referee"));
        refereeRepository.deleteById(id);
    }

}
