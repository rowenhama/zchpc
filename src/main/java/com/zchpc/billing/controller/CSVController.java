package com.zchpc.billing.controller;

import com.zchpc.billing.helper.CSVHelper;
import com.zchpc.billing.message.ResponseMessage;
import com.zchpc.billing.model.Accounts;
import com.zchpc.billing.repo.AccountsRepo;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import com.zchpc.billing.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
@Validated
public class CSVController {

    Boolean status;
    private String message, button;

    @Autowired
    CSVService fileService;

    @Autowired
    AccountsRepo accountsRepo;

    @GetMapping("/home")
    public String home() {
        return "uploadFileFrontEnd";
    }

    @PostMapping("/uploadFileFrontEnd")
    public String uploadFileFrontEnd(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadStatus";
        }

        status = fileService.save(file);

        if (status.equals(false)) {
            button = "Retry again";
            message = "Error occured. Kindly check if subscriber exists or try again later.";
            return "redirect:/uploadMessage";
        } else if (status.equals(true)) {
            button = "Upload another file";
            message = "All subscribers were successfully awarded their subscriptions.";
            return "redirect:/uploadMessage";
        }

        redirectAttributes.addFlashAttribute("message",
                status);

        return "redirect:/uploadStatus";

    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

//        fileService.save(file);

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/uploadFileFrontEnd")
    public String uploadedStaff(Model model){
        model.addAttribute("results", status);
        return "uploadStatus";
    }

    @GetMapping("/uploadMessage")
    public String uploadSuccess(Model model) {
        if (message != null && button != null) {
            model.addAttribute("success", !status.equals(false));
            model.addAttribute("message", message);
            model.addAttribute("button", button);
            return "upload";
        }

        return "redirect:/home";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(Model model) {
        model.addAttribute("results", status);
        return "uploadStatus";
    }

    @GetMapping("/get-all-")
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        try {
            List<Accounts> tutorials = fileService.getAllTutorials();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "accounts.csv";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}