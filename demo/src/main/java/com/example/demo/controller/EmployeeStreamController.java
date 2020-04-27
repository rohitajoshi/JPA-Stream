package com.example.demo.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class EmployeeStreamController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeStreamController.class);



	@Autowired
    private EmployeeService employeeService;
	

	@GetMapping(value = "/employee", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<StreamingResponseBody> getAllEmployee(HttpServletResponse response) {

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"userListing.csv\"");

		StreamingResponseBody stream = this::writeTo;

		logger.info("steaming response {} ", stream);
		return new ResponseEntity<>(stream, HttpStatus.OK);
	}

	private void writeTo(OutputStream outputStream) {
        employeeService.doWrite(outputStream);

	}
}