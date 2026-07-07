package com.cod.asyncmicroservice.controller;

import com.cod.asyncmicroservice.business.CustomerService;
import com.cod.asyncmicroservice.business.FileService;
import com.cod.asyncmicroservice.domain.Customer;
import com.cod.asyncmicroservice.domain.FileData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocking")
public class BlockingController {

    private static final Logger log = LoggerFactory.getLogger(BlockingController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    FileService fileService;

    @GetMapping("/customers/{name}")
    public List<Customer> getCustomerByName(@PathVariable String name) {
        log.info("Getting customer by name {} ", name);
        List<Customer> customerList = customerService.getCustomerByName(name);
        log.info("Received {} customers by name {}", customerList.size(), name);
        return customerList;
    }

    @PostMapping("/customers/save")
    public Customer addCustomer(@RequestBody Customer customer) {
        log.info("Adding user {} to the Database", customer.getName());
        return customerService.addCustomer(customer);
    }

    @GetMapping("/fileread")
    public String readFile() {
        log.info("reading file request");
        return fileService.readFile();
    }

    @PostMapping("/filewrite")
    public boolean writeFile(@RequestBody FileData fileData) {
        log.info("Write data {} to File", fileData);
        return fileService.writeFile(fileData);
    }
}
