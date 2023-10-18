package org.example.springboot.fraud;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckHistoryService fraudCheckHistoryService) {

    @PostMapping(path= "{costomerId}")
    public FraudCheckResponse isFraudster(@PathVariable ("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);

    }

}
