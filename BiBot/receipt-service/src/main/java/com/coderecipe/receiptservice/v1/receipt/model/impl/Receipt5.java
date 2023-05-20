package com.coderecipe.receiptservice.v1.receipt.model.impl;


import com.coderecipe.receiptservice.v1.receipt.dto.ReceiptContent;
import com.coderecipe.receiptservice.v1.receipt.dto.vo.ReceiptReq;
import com.coderecipe.receiptservice.v1.receipt.model.IReceipt;
import com.coderecipe.receiptservice.v1.receipt.receiptsform.utils.ReceiptUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Receipt5 implements IReceipt {

    private ReceiptReq.CreateMockReceiptReq req;

    public String font() {
        return "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
                + "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
                + "<link href=\"https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap\" rel=\"stylesheet\">";
    }

    public String css() {
        return " .right { text-align: right;}"
                + " .big { font-size: 13px; font-weight: bold; text-align: center;}"
                + " .store { font-size: 13px; font-weight: bold; text-align: center;}"
                + " .small { font-size: 10px;}"
                + " .center { text-align: center; }"
                + " .left { text-align : left;}"
                + " .text { font-size : 10px; color: gray; text-align: center;}"
                + " table { border: solid 1px;}"
                + " td { font-size : 10px;}"
                + " body { font: 'Nanum Gothic Coding';}";
    }

    public String html() {
        ReceiptContent receiptContent = ReceiptUtils.getReceiptData();
        StringBuilder productRow = new StringBuilder();
        for (ReceiptReq.ProductOrderList order : req.getProductOrderList()) {
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
                + "             <td rowspan=\"2\" colspan=\"2\" class=\"store\">"
                + req.getPaymentDestination() + "</td>\n"
                + "             <td colspan=\"2\" class=\"big\">[총 구매액]</td>\n"
                + "         <tr>\n"
                + "             <td colspan=\"2\" class=\"big\" > &nbsp; " + req.getTotalPrice()
                + "</td>\n"
                + "         </tr>\n"
                + "     </th>\n"
                + "         </tr>\n"
                + "             <td colspan=\"4\"><hr></td>"
                + "         </tr>"
                + "     <tr>\n"
                + "         <td class=\"center\"><b>상품명</b></td>\n"
                + "         <td class=\"center\"><b>" + receiptContent.getAmountName() + "</b></td>\n"
                + "         <td class=\"center\"><b>" + receiptContent.getCountName() + "</b></td>\n"
                + "         <td class=\"center\"><b>" + receiptContent.getPriceName() + "</b></td>\n"
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
