package com.test.iac.phonebook.controller;

import com.test.iac.phonebook.dto.request.CreateRecordRequest;
import com.test.iac.phonebook.dto.request.UpdateRecordRequest;
import com.test.iac.phonebook.dto.response.RecordInfoResponse;
import com.test.iac.phonebook.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecordInfoResponse createRecord(@RequestBody CreateRecordRequest createRecordRequest){
        return recordService.create(createRecordRequest);
    }

    @PutMapping("/{id}")
    public RecordInfoResponse updateRecord(@PathVariable UUID id, @RequestBody UpdateRecordRequest updateRecordRequest){
        return recordService.update(id, updateRecordRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable UUID id){
        recordService.deleteById(id);
    }

    @GetMapping("/{id}")
    public RecordInfoResponse getRecord(@PathVariable UUID id){
        return recordService.getById(id);
    }

    @GetMapping
    public List<RecordInfoResponse> getRecords(@RequestParam(required = false) String name){
        if(name == null)
            return recordService.getAllActive();
        return recordService.getAllByName(name);
    }

    @GetMapping("/async-update")
    public CompletableFuture<List<RecordInfoResponse>> asyncUpdate() throws InterruptedException{
        return recordService.asyncUpdateAllActive();
    }
}
