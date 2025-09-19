package com.proyects.pasarelapagosalpha.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qrCodeUrl; // URL del código QR generado por VPay
    private String status; // Estado del pago: PENDING, COMPLETED, FAILED

    private String amount; // Monto del pago
    private String currency; // Moneda del pago (Ej: USD, EUR, BOB)
    private String description; // Descripción del pago

    private String idQr;

    private LocalDateTime createdAt;

    private LocalDateTime paymentAt;

}
