package com.test.iac.phonebook.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRecordRequest {
    @Size(max = 50, message = "Длина имени не может быть больше 50 символов")
    private String name;
    @Pattern(regexp = "^((8|\\+7)\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2})$", message = "Номер телефона не соответствует паттерну")
    private String phoneNumber;
}
