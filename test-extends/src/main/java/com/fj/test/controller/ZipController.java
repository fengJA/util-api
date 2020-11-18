package com.fj.test.controller;

import com.fj.test.entity.PdfScanModelVo;
import com.fj.test.service.pdfzip.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
public class ZipController {
    @Autowired
    private ZipService zipService;

    @PostMapping(value = "/zip-pdf")
    public void zipPdf() {
        zipService.zipPdf();
    }


    public static void main(String[] args) {

        System.out.println(ss());

    }

    private static PdfScanModelVo ss(){

        ArrayList<Object> objects = new ArrayList<>();
        PdfScanModelVo pdfScanModelVo = new PdfScanModelVo("", "", "", null);
        return pdfScanModelVo;
    }

}
