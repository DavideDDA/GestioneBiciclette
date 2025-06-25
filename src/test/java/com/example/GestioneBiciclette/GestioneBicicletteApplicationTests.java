package com.example.GestioneBiciclette;

import com.example.GestioneBiciclette.security.controller.AuthController;
import com.example.GestioneBiciclette.security.payload.RegisterDto;
import com.example.GestioneBiciclette.security.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class GestioneBicicletteApplicationTests {

	// Consente di simulare le richieste http
	@Autowired private MockMvc mockMvc;

	@MockitoBean
	AuthService authService;

	//Converte oggetti java in JSON e viceversa
	@Autowired
	ObjectMapper objectMapper;

/*	@Test
	void contextLoads() {
	}*/


	@Test
	void testRegisterUser() throws Exception {
		RegisterDto user = new RegisterDto("Mario", "mariorossi", "mario@example.com", "pwd", Set.of("CLIENTE"));

		when(authService.register(Mockito.any(RegisterDto.class)))
				.thenReturn("User registered successfully!.");

		mockMvc.perform(post("/api/auth/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andExpect(content().string("User registered successfully!."));

		verify(authService, times(1)).register(Mockito.any(RegisterDto.class));
	}

}
