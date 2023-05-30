package com.coderecipe.receiptservice.v1.receipt.receiptsform.worker;



import com.coderecipe.receiptservice.v1.receipt.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class SelectForm {

    private  final TemplateEngine templateEngine;

    public  boolean createReceiptImage(CreateMockReceiptReq req) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(req, Map.class);

        Context context = new Context();
        context.setVariables(map);

        String processedHtml = templateEngine.process("end.html", context);

        ConverterProperties properties = new ConverterProperties();

        //pdf 페이지 크기를 조정
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

        // 이미지 파일로 변환할 PDF파일의 경로
        String pdfFile = req.getPaymentCode() + ".pdf";
        // 생성될 이미지 파일을 저장할 경로
        String outputFile = req.getPaymentCode() + ".png";

        PDDocument document2 = PDDocument.load(new File(pdfFile));
        PDFRenderer pdfRenderer = new PDFRenderer(document2);

        // 첫 페이지를 300dpi로 렌더링하여 이미지화.
        BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
        // 렌더링된 페이지를, outputFile 경로에 300dpi로 이미지로 저장.
        ImageIOUtil.writeImage(image, outputFile, 300);
        pdf.close();
        document2.close();

        File file = new File(pdfFile);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("파일이 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("파일 삭제 중 오류가 발생했습니다.");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }

        return true;
    }
}