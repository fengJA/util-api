package com.fj.test.controller;

import com.fj.test.service.pdfscan.PdfScanService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PdfScanController {

    @Autowired
    private PdfScanService pdfScanService;

    @PostMapping(value = "/upload-pdf")
    public void uploadPdf(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        pdfScanService.uploadPdf(file);
    }

    @GetMapping(value = "/scanPdf")
    public void excelPdf(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        pdfScanService.scanPdf();
    }

    @PostMapping(value = "/itext-scanPdf")
    public void itextScanPdf(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        pdfScanService.itextScanPdf(file);
    }
}
