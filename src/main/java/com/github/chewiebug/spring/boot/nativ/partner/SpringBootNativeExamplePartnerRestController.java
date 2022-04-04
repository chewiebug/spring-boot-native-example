package com.github.chewiebug.spring.boot.nativ.partner;

import com.github.chewiebug.spring.boot.nativ.partner.model.Partner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partners")
public class SpringBootNativeExamplePartnerRestController {

    @GetMapping("/{id}")
    public Partner read(@PathVariable String id) {
        return new Partner("lastName", "firstName", id);
    }

}
