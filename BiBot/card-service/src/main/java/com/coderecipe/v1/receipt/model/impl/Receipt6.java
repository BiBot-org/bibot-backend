package com.coderecipe.v1.receipt.model.impl;

import com.coderecipe.v1.payment.dto.vo.PaymentReq.CreateMockReceiptReq;
import com.coderecipe.v1.payment.dto.vo.PaymentReq.ProductOrderList;
import com.coderecipe.v1.receipt.dto.ReceiptDTO;
import com.coderecipe.v1.receipt.model.IReceipt;
import com.coderecipe.v1.receipt.receiptsForm.utils.ReceiptUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Receipt6 implements IReceipt {

    private CreateMockReceiptReq req;

    public String font() {
        return "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
            + "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
            + "<link href=\"https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap\" rel=\"stylesheet\">";
    }

    public String css() {
        return " .right { text-align: right;}"
            + " .big { font-size: 13px; font-weight: bold;text-align: center;}"
            + " .bigStore { font-size: 13px; font-weight: bold; text-align : center;}"
            + " .small { font-size: 10px;}"
            + " .center { text-align: center;}"
            + " .left { text-align : left;}"
            + " .text { font-size : 10px; color: gray; text-align: center;}"
            + " table { border: solid 1px;}"
            + " td { font-size : 10px;}"
            + " body { font: 'Nanum Gothic Coding';}";
    }

    public String html() {
        ReceiptDTO receiptDTO = ReceiptUtils.getReceiptData();
        StringBuilder productRow = new StringBuilder();
        for (ProductOrderList order : req.getProductOrderList()) {
            productRow.append("<tr>"
                + "<td class=\"center\"><b>" + order.getProductName() + "</b></td>"
                + "<td class=\"center\"><b>" + order.getProductCost() + "</b></td>"
                + "<td class=\"center\"><b>" + order.getCount() + "</b></td>"
                + "<td class=\"center\"><b>" + order.getAmount() + "</b></td>"
                + "</tr>");
        }

        return "<table>"
            + " <tbody>\n"
            + "     <th>\n"
            + "         <tr>\n"
            + "             <td colspan=\"2\" rowspan=\"2\" class=\"bigStore\">"
            + req.getPaymentDestination()
            + "</td>\n"
            + "             <td colspan=\"2\" class=\"big\" >[총 구매액]</td>\n"
            + "         </tr>\n"
            + "         <tr>\n"
            + "             <td colspan=\"2\" class=\"big\"> &nbsp; " + req.getTotalPrice()
            + "</td>\n"
            + "         </tr>\n"
            + "     </th>\n"
            + "         </tr>\n"
            + "             <td colspan=\"4\"><hr></td>"
            + "         </tr>"
            + "     <tr>\n"
            + "         <td class=\"center\"><b>상품명</b></td>\n"
            + "         <td class=\"center\"><b>" + receiptDTO.getAmountName() + "</b></td>\n"
            + "         <td class=\"center\"><b>" + receiptDTO.getCountName() + "</b></td>\n"
            + "         <td class=\"center\"><b>" + receiptDTO.getPriceName() + "</b></td>\n"
            + "     </tr>\n"
            + "     <tr>"
            + "         <td colspan=\"4\">"
            + "         <hr>"
            + "         </td>"
            + "     </tr>\n"
            + productRow
            + "     <tr>\n"
            + "         <td colspan=4>--------------------------------------------------------------------------------</td>\n"
            + "     </tr>\n"
            + "         <tr>\n"
            + "             <td colspan=\"1\">[구매일자]</td>\n"
            + "             <td colspan=\"3\" class=\"right\">" + req.getPaymentDateStr()
            + "</td>\n"
            + "         </tr>\n"
            + "         <tr>\n"
            + "             <td colspan=\"2\">[사업자번호]</td>\n"
            + "             <td colspan=\"2\" class=\"right\">" + req.getBusinessLicense()
            + "</td>\n"
            + "         </tr>\n"
            + "         <tr>\n"
            + "             <td colspan=\"1\">[대표자]</td>\n"
            + "             <td colspan=\"3\" class=\"right\">" + req.getRepresentationName()
            + "</td>\n"
            + "         </tr>\n"
            + "         <tr>\n"
            + "             <td colspan=\"1\">[주소]</td>"
            + "             <td colspan=\"3\" class=\"right\">" + req.getAddress() + "</td>"
            + "         </tr>\n"
            + "     <tr>\n"
            + "         <td colspan=4>--------------------------------------------------------------------------------</td>\n"
            + "     </tr>\n"
            + "     <tr>\n"
            + "         <td colspan=1>[승인 번호]</td>\n"
            + "         <td colspan=3 class=\"right\">" + req.getPaymentCode() + "</td>\n"
            + "     </tr>\n"
            + "     <tr>\n"
            + "         <td colspan=1>[카드 종류]</td>\n"
            + "         <td colspan=3 class=\"right\">" + req.getPaymentCardCompany() + "</td>\n"
            + "     </tr>\n"
            + "     <tr>\n"
            + "         <td colspan=4>--------------------------------------------------------------------------------</td>\n"
            + "     </tr>\n"
            + "     <tr>\n"
            + "         <td colspan=4 class=\"center\" class=\"text\"> 본 영수증은 거래의 참고용으로만 사용하시기 바랍니다.</td>"
            + "     </tr>\n"
            + "         <td colspan=4>--------------------------------------------------------------------------------</td>\n"
            + "     </tr>\n"
            + " </tbody>\n"
            + "</table>\n";
    }

}
