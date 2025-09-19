package com.proyects.pasarelapagosalpha.controller;

import com.proyects.pasarelapagosalpha.model.request.CallbackRequest;
import com.proyects.pasarelapagosalpha.model.response.CallbackResponse;
import com.proyects.pasarelapagosalpha.service.CallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback")
public class CallbackController {

    @Autowired
    private CallbackService callbackService;

    @PutMapping("/vpay")
    public ResponseEntity<CallbackResponse> handleVPayCallback(@RequestBody CallbackRequest request) {
        try {
            callbackService.processPaymentConfirmation(request);
            CallbackResponse response = new CallbackResponse("OK", true);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            CallbackResponse response = new CallbackResponse("Error processing callback", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
