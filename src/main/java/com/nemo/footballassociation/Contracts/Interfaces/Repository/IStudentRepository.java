package com.nemo.footballassociation.Contracts.Interfaces.Repository;
import com.nemo.footballassociation.Contracts.Modules.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    public boolean existsByEmail(String email);

    public List<Student> findByEmail(String email);

    @Query("select max(s.id) from Student s")
    public Integer findMaxId();
}
