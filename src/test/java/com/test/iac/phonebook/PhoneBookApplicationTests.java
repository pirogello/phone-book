package com.test.iac.phonebook;

import com.test.iac.phonebook.dto.request.CreateRecordRequest;
import com.test.iac.phonebook.dto.request.UpdateRecordRequest;
import com.test.iac.phonebook.dto.response.RecordInfoResponse;
import com.test.iac.phonebook.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PhoneBookApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PhoneBookApplicationTests {

    @Autowired
    private RecordService recordService;


    @Test
    public void shouldReturnCorrectRecordInfoResponseAfterCreateRecord() {
        String name = "TestName";
        String phone = "+7(999)999-99-99";
        CreateRecordRequest request = new CreateRecordRequest(name, phone);
        RecordInfoResponse response = recordService.create(request);
        assertNotNull(response.getId());
        assertNotNull(response.getCreated());
        assertNotNull(response.getUpdated());
        assertEquals(name, response.getName());
        assertEquals(phone, response.getPhoneNumber());
    }
    @Test
    public void shouldReturnRecordById(){
        // create record
        String name = "TestName";
        String phone = "+7(999)999-99-99";
        CreateRecordRequest request = new CreateRecordRequest(name, phone);
        RecordInfoResponse createdRecord = recordService.create(request);
        // get record
        RecordInfoResponse response = recordService.getById(createdRecord.getId());
        // check
        assertEquals(createdRecord.getName(), response.getName());
        assertEquals(createdRecord.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(createdRecord.getCreated().truncatedTo(ChronoUnit.SECONDS), response.getCreated().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(createdRecord.getUpdated().truncatedTo(ChronoUnit.SECONDS), response.getUpdated().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(createdRecord.getId(), response.getId());
    }

    public void shouldUpdateRecordWithNewName() {
        // create record
        String name = "TestName";
        String phone = "+7(999)999-99-99";
        CreateRecordRequest request = new CreateRecordRequest(name, phone);
        RecordInfoResponse createdRecord = recordService.create(request);
        // update record
        String newName = "updatedTestName";
        UpdateRecordRequest updateRecordRequest = new UpdateRecordRequest();
        updateRecordRequest.setName(newName);
        recordService.update(createdRecord.getId(), updateRecordRequest);
        // check
        RecordInfoResponse updatedRecord = recordService.getById(createdRecord.getId());
        assertEquals(newName, updatedRecord.getName());
        assertEquals(createdRecord.getPhoneNumber(), updatedRecord.getPhoneNumber());
        assertEquals(createdRecord.getId(), updatedRecord.getId());
        assertEquals(createdRecord.getCreated().truncatedTo(ChronoUnit.SECONDS), updatedRecord.getCreated().truncatedTo(ChronoUnit.SECONDS));
        assertNotEquals(createdRecord.getUpdated().truncatedTo(ChronoUnit.SECONDS), updatedRecord.getUpdated().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void shouldDeleteRecord() {
        // create record
        String name = "TestName";
        String phone = "+7(999)999-99-99";
        CreateRecordRequest request = new CreateRecordRequest(name, phone);
        RecordInfoResponse response = recordService.create(request);
        // delete record
        recordService.deleteById(response.getId());
        // check
        List<RecordInfoResponse> records = recordService.getAll();
        assertEquals(0, records.size());
    }

    @Test
    public void shouldReturnAllDeletedRecords() {
        // create record
        String name = "TestName";
        String phone = "+7(999)999-99-99";
        CreateRecordRequest request = new CreateRecordRequest(name, phone);
        RecordInfoResponse response = recordService.create(request);
        // delete record
        recordService.deleteById(response.getId());
        // check
        List<RecordInfoResponse> deleted = recordService.getAllDeleted();
        assertEquals(1, deleted.size());
    }

}
