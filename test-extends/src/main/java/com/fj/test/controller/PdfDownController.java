package com.fj.test.controller;

import com.fj.test.service.PdfDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PdfDownController {

    @Autowired
    private PdfDownService pdfDownService;

//    @RequestMapping(value = "/exportPdf.html")
    @GetMapping(value = "/exportPdf.html")
    public void excelPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.pdfDownService.exportPdf(request, response);
    }


}
