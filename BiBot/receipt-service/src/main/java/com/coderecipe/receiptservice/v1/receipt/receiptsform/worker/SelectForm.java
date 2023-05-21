package com.coderecipe.receiptservice.v1.receipt.receiptsform.worker;


import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.model.IReceipt;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.utils.ReceiptFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelectForm {

    public boolean createReceiptImage(ReceiptReq.CreateMockReceiptReq req) throws Exception {
        ReceiptFactory receiptFactory = new ReceiptFactory();
        IReceipt iReceipt = receiptFactory.createReceipt(req);

        String font = iReceipt.font();
        String css = iReceipt.css();
        String html = iReceipt.html();

        String formatedNow = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String styledHtml =
                "<html><head>"
                        + font + "<style>"
                        + css + "</style></head><body>"
                        + html + "</body></html>";

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(styledHtml);
        editorPane.setSize(new Dimension(346, 445 + req.getProductOrderList().size() * 26));

        BufferedImage image = new BufferedImage(editorPane.getWidth(), editorPane.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        editorPane.print(graphics);

        ImageIO.write(image, "png", new File(formatedNow + "_test.png"));

        return true;
    }

}
