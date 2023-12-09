package com.test.iac.phonebook.service;

import com.test.iac.phonebook.dto.request.CreateRecordRequest;
import com.test.iac.phonebook.dto.request.UpdateRecordRequest;
import com.test.iac.phonebook.dto.response.RecordInfoResponse;
import com.test.iac.phonebook.exception.ModelNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RecordService {
    RecordInfoResponse create(CreateRecordRequest dto);
    RecordInfoResponse update(UUID id, UpdateRecordRequest dto) throws ModelNotFoundException;
    void deleteById(UUID id);
    RecordInfoResponse getById(UUID id);
    RecordInfoResponse getByPhoneNumber(String phoneNumber);
    List<RecordInfoResponse> getAllByName(String name);
    List<RecordInfoResponse> getAll();
    CompletableFuture<List<RecordInfoResponse>> asyncUpdateAllActive() throws InterruptedException;
    List<RecordInfoResponse> getAllDeleted();

}
