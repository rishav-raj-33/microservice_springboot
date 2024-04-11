package com.microservice.customer.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class CustomerDto {
	@NotNull(message = "Message must not be Empty")
	private String name;
	
	@Email(message = "E-mail is Not Valid")
	private String email;
	@NotEmpty
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 8  ,message = "Password should contain atleast 8 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Password format:Must be between 8 and 20 characters long.\r\n"
			+ "Contains at least one digit (0-9).\r\n"
			+ "Contains at least one lowercase letter (a-z).\r\n"
			+ "Contains at least one uppercase letter (A-Z).\r\n"
			+ "Contains at least one special character from the specified list.")
	private String pasword;
}
