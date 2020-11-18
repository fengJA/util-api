package com.fj.test.service.pdfscan;

import org.springframework.web.multipart.MultipartFile;

public interface PdfScanService {
    /**
     * 扫描PDF
     */
    void scanPdf();

    /**
     * 上传PDF
     * @param file
     */
    void uploadPdf(MultipartFile file) throws Exception;

    /**
     * itext解析PDF
     * @param file
     */
    void itextScanPdf(MultipartFile file);
}
