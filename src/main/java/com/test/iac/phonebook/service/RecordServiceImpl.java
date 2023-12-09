package com.test.iac.phonebook.service;

import com.test.iac.phonebook.dto.request.CreateRecordRequest;
import com.test.iac.phonebook.dto.request.UpdateRecordRequest;
import com.test.iac.phonebook.dto.response.RecordInfoResponse;
import com.test.iac.phonebook.exception.ModelNotFoundException;
import com.test.iac.phonebook.model.Status;
import com.test.iac.phonebook.model.entity.Record;
import com.test.iac.phonebook.repository.RecordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepo recordRepo;

    @Override
    public RecordInfoResponse create(CreateRecordRequest dto) {
        Record entity = new Record(
                dto.getName(),
                dto.getPhoneNumber()
        );
        var savedEntity = recordRepo.save(entity);
        return RecordInfoResponse.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .phoneNumber(savedEntity.getPhoneNumber())
                .created(savedEntity.getCreated())
                .updated(savedEntity.getUpdated())
                .build();
    }

    @Override
    public RecordInfoResponse update(UUID id, UpdateRecordRequest dto) throws ModelNotFoundException {
        Record entity = recordRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Record with id=" + id + " not found."));

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());

        var savedEntity = recordRepo.save(entity);
        return RecordInfoResponse.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .phoneNumber(savedEntity.getPhoneNumber())
                .created(savedEntity.getCreated())
                .updated(savedEntity.getUpdated())
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        recordRepo.deleteById(id);
//        Record entity = recordRepo.findById(id).orElse(null);
//        if (entity == null) return;
//        entity.setStatus(Status.DELETED);
//        recordRepo.save(entity);
    }

    @Override
    public RecordInfoResponse getById(UUID id) {
        Record entity = recordRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Record with id=" + id + " not found."));
        return entityToResponse(entity);
    }

    @Override
    public RecordInfoResponse getByPhoneNumber(String phoneNumber) {
        Record entity = recordRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ModelNotFoundException("Record with phone_number=" + phoneNumber + " not found."));
        return entityToResponse(entity);
    }

    @Override
    public List<RecordInfoResponse> getAllByName(String name) {
        List<Record> entities = recordRepo.findAllByName(name);
        return entities.stream()
                .map(this::entityToResponse)
                .toList();
    }

    @Override
    public List<RecordInfoResponse> getAllActive() {
        return recordRepo.findAll()
                .stream()
                .map(this::entityToResponse)
                .toList();
    }

    @Async
    @Override
    public CompletableFuture<List<RecordInfoResponse>> asyncUpdateAllActive() throws InterruptedException {
        List<Record> records = recordRepo.findAll();
        records.forEach(r -> r.setFieldForAsyncUpdate(r.getFieldForAsyncUpdate() + 1));
        List<Record> savedRecords = recordRepo.saveAll(records);
        // for testing async
        Thread.sleep(3000);
        List<RecordInfoResponse> response = savedRecords.stream()
                .map(this::entityToResponse)
                .toList();
        return CompletableFuture.completedFuture(response);
    }


    private RecordInfoResponse entityToResponse(Record entity){
        return RecordInfoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .build();
    }


}
