package com.fj.test.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PdfDownService {
    void exportPdf(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
