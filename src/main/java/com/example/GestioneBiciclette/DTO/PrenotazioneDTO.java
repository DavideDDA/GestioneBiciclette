package com.example.GestioneBiciclette.DTO;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {
        @NotNull
        @Future(message = "La data di fine deve essere nel futuro")
        private LocalDateTime dataFine;

        @NotNull(message = "L'ID utente è obbligatorio")
        private Long userId;

        @NotNull(message = "L'ID della bicicletta è obbligatorio")
        private Long biciclettaId;

        @NotNull(message = "L'ID del parcheggio di arrivo è obbligatorio")
        private Long parcheggioArrivoId;
}
