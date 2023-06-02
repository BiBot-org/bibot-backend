package com.coderecipe.receiptservice.v1.receipt.receiptsform.worker;

import com.coderecipe.global.constant.enums.CustomLogFormat;
import com.coderecipe.global.utils.StringUtils;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class SelectForm {
    @Value("${gcp.bucket.name}")
    private String bucketName;
    private final Storage storage;

    private final TemplateEngine templateEngine;

    public String createReceiptImage(ReceiptReq.CreateMockReceiptReq req) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(req, Map.class);

        Context context = new Context();
        context.setVariables(map);

        String processedHtml = templateEngine.process("end.html", context);

        ConverterProperties properties = new ConverterProperties();

        List<IElement> elements = HtmlConverter.convertToElements(processedHtml, properties);
        PdfDocument pdf = new PdfDocument(new PdfWriter(req.getPaymentCode() + ".pdf"));

        PageSize customPageSize = new PageSize(281.25f, 375);
        pdf.setDefaultPageSize(customPageSize);

        Document document = new Document(pdf);
        document.setMargins(0, 0, 0, 0);
        for (IElement element : elements) {
            document.add((IBlockElement) element);
        }
        document.close();

        String pdfFile = req.getPaymentCode() + ".pdf";
        String outputFile = req.getPaymentCode() + ".png";

        PDDocument document2 = PDDocument.load(new File(pdfFile));
        PDFRenderer pdfRenderer = new PDFRenderer(document2);

        BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
        ImageIOUtil.writeImage(image, outputFile, 300);
        pdf.close();
        document2.close();

        File file = new File(pdfFile);
        if (file.exists()) {
            if (!file.delete()) {
                log.error(String.format(CustomLogFormat.GENERATE_RECEIPT_ERROR.getFormatString(), req.getPaymentCode()));
            } else {
                log.info(String.format(CustomLogFormat.GENERATE_RECEIPT_SUCCESS.getFormatString(), req.getPaymentCode()));
            }
        }

        MultipartFile imageFile = new MockMultipartFile(outputFile, new FileInputStream(outputFile));
        String imagePath = String.format("RECEIPT_IMAGE/%s/%s", StringUtils.generateDateString(), imageFile.getName());
        BlobId blobId = BlobId.of("bibot_receipt", imagePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(imageFile.getContentType()).build();
        Blob blob = storage.create(blobInfo, imageFile.getBytes());
        log.info(blob.toString());
        log.info(StringUtils.generateCloudStorageUrl("bibot_receipt", imagePath));


        return StringUtils.generateCloudStorageUrl("bibot_receipt", imagePath);
    }


}