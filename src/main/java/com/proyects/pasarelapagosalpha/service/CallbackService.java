package com.proyects.pasarelapagosalpha.service;

import com.proyects.pasarelapagosalpha.model.Payment;
import com.proyects.pasarelapagosalpha.model.request.CallbackRequest;
import com.proyects.pasarelapagosalpha.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CallbackService {

    @Autowired
    private PaymentRepository paymentRepository;


    public void processPaymentConfirmation(CallbackRequest request) {
        System.out.println("Procesando confirmación de pago:");
        System.out.println("ID QR: " + request.getIdQr());
        System.out.println("ID Transacción VPay: " + request.getIdTransactionVpay());
        System.out.println("Fecha: " + request.getTransactionDateTime());
        System.out.println("Canal: " + request.getChannelTransaccion());
        System.out.println("Glosa: " + request.getGloss());
        System.out.println("Datos adicionales: " + request.getAdditionalData());


        Payment payment = paymentRepository.findByIdQr(request.getIdQr());
        payment.setStatus("PAY");
        payment.setPaymentAt(LocalDateTime.now());

        paymentRepository.save(payment);
    }
}
