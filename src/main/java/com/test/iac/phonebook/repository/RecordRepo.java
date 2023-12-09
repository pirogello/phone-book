package com.test.iac.phonebook.repository;

import com.test.iac.phonebook.model.Status;
import com.test.iac.phonebook.model.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecordRepo extends JpaRepository<Record, UUID> {
    Optional<Record> findByPhoneNumber(String phoneNumber);
    List<Record> findAllByName(String name);
    @Query(
            value = "SELECT * FROM t_record r WHERE r.status = 'DELETED'",
            nativeQuery = true)
    List<Record> findAllDeleted();
}
