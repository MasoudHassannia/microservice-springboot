package org.example.springboot.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(path= "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable ("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("===== Customer ID is {} ======",customerId);
        return new FraudCheckResponse(isFraudulentCustomer);

    }

}
