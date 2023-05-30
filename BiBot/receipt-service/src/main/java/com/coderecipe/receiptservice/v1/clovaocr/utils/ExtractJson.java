package com.coderecipe.receiptservice.v1.clovaocr.utils;

import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Address;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Item;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.PaymentInfo;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Receipt;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.StoreInfo;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.SubResult;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.Tel;
import com.coderecipe.receiptservice.v1.clovaocr.dto.vo.OCRtoJSONRes.TotalPrice;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtractJson {

    @Builder.Default
    private String storeName = "";
    @Builder.Default
    private String storeSubName = "";
    @Builder.Default
    private String bizNum = "";
    @Builder.Default
    private List<String> addresses = new ArrayList<>();
    @Builder.Default
    private List<String> tel = new ArrayList<>();
    @Builder.Default
    private String date = "";
    @Builder.Default
    private String time = "";
    @Builder.Default
    private String confirmNum = "";
    @Builder.Default
    private String cardCompany = "";
    @Builder.Default
    private String cardNum = "";
    @Builder.Default
    private ArrayList<SubResult> items = new ArrayList<>();
    @Builder.Default
    private String totalPrice = "";

    public static ExtractJson of(Receipt receipt) {

        ExtractJsonBuilder builder = ExtractJson.builder();
        if (receipt.getResult().getStoreInfo() != null) {
            StoreInfo storeInfo = receipt.getResult().getStoreInfo();
            builder = getStoreInfoBuilder(builder, storeInfo);
        }

        if (receipt.getResult().getPaymentInfo() != null) {
            PaymentInfo paymentInfo = receipt.getResult().getPaymentInfo();
            builder = getPaymentInfoBuilder(builder, paymentInfo);
        }

        if (receipt.getResult().getSubResults() != null) {
            ArrayList<SubResult> subResult = receipt.getResult().getSubResults();
            builder = getSubResults(builder, subResult);
        }
        if (receipt.getResult().getTotalPrice() != null) {
            TotalPrice totalPrice = receipt.getResult().getTotalPrice();
            builder = getTotalPrice(builder, totalPrice);
        }

        return builder.build();

    }

    public static ExtractJsonBuilder getStoreInfoBuilder(ExtractJsonBuilder builder,
        StoreInfo storeInfo) {
        if (storeInfo.getName() != null && storeInfo.getName().getText() != null) {
            builder.storeName(storeInfo.getName().getText());
        }

        if (storeInfo.getSubName() != null && storeInfo.getSubName().getText() != null) {
            builder.storeSubName(storeInfo.getSubName().getText());
        }

        if (storeInfo.getBizNum() != null && storeInfo.getBizNum().getText() != null) {
            builder.bizNum(storeInfo.getBizNum().getText());
        }

        if (storeInfo.getAddresses() != null) {
            builder.addresses(storeInfo.getAddresses().stream().map(Address::getText).toList());
        }

        if (storeInfo.getAddresses() != null) {
            builder.tel(storeInfo.getTel().stream().map(Tel::getText).toList());
        }

        return builder;
    }

    public static ExtractJsonBuilder getPaymentInfoBuilder(ExtractJsonBuilder builder,
        PaymentInfo paymentInfo) {
        if (paymentInfo.getDate() != null && paymentInfo.getDate().getText() != null) {
            builder.date(paymentInfo.getDate().getText());
        }

        if (paymentInfo.getTime() != null && paymentInfo.getTime().getText() != null) {
            builder.time(paymentInfo.getTime().getText());
        }

        if (paymentInfo.getConfirmNum() != null && paymentInfo.getConfirmNum().getText() != null) {
            builder.confirmNum(paymentInfo.getConfirmNum().getText());
        }

        if (paymentInfo.getCardInfo().getCompany() != null
            && paymentInfo.getCardInfo().getCompany().getText() != null) {
            builder.cardCompany(paymentInfo.getCardInfo().getCompany().getText());
        }

        if (paymentInfo.getCardInfo().getCompany() != null
            && paymentInfo.getCardInfo().getNumber().getText() != null) {
            builder.cardNum(paymentInfo.getCardInfo().getNumber().getText());
        }

        return builder;
    }

    public static ExtractJsonBuilder getSubResults(ExtractJsonBuilder builder,
        ArrayList<SubResult> subResult) {
        if (subResult != null) {
            ArrayList<SubResult> results = subResult;
            List<Item> result = new ArrayList<>();
            results.forEach(e -> {
                List<Item> itemList = e.getItems();
                result.addAll(itemList);
            });

            builder.items(subResult);
        }

        return builder;
    }

    public static ExtractJsonBuilder getTotalPrice(ExtractJsonBuilder builder,
        TotalPrice totalPrice) {
        if (totalPrice.getPrice() != null && totalPrice.getPrice().getText() != null) {
            builder.totalPrice(totalPrice.getPrice().getText());
        }
        return builder;
    }

}
