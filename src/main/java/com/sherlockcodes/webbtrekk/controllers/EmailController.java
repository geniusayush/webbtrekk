package com.sherlockcodes.webbtrekk.controllers;

import com.sherlockcodes.webbtrekk.entity.Email;
import com.sherlockcodes.webbtrekk.service.EmailService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sherlockcodes.webbtrekk.utils.Utils.validateRequest;

@RestController
@RequestMapping("/email")
@Api(value = "manage email", description = "basic controller that lets users manage emails")

public class EmailController {

    private static final Logger logger = LogManager.getLogger(EmailController.class);
    @Autowired
    private EmailService generatorService;

    @PostMapping("/send")
    @ApiOperation(value = "send an email")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public ResponseEntity<Object> sendEmail(@RequestBody @ApiParam("email") Email email) {
        validateRequest(email);
        generatorService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}



