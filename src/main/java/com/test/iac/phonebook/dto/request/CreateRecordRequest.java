package com.test.iac.phonebook.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecordRequest {
    @Size(max = 50)
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^((8|\\+7)\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2})$", message = "Номер телефона не соответствует паттерну")
    private String phoneNumber;
}
