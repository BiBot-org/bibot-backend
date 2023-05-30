package com.coderecipe.receiptservice.v1.clovaocr.dto.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown =true)
public class OCRtoJSONRes {

    public String version;
    public String requestId;
    public long timestamp;
    public ArrayList<Image> images;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Address {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class BizNum {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class BoundingPoly {
//
//        public ArrayList<Vertex> vertices;
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class CardInfo {

        public Company company;
        public Number number;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Code {

        public String text;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Company {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class ConfirmNum {

        public String text;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Count {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Date {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Formatted {

        public String value;
//        public String year;
//        public String month;
//        public String day;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Image {

        public Receipt receipt;
        public String uid;
        public String name;
        public String inferResult;
        public String message;
//        public ValidationResult validationResult;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Item {

        public Name name;
        public Code code;
        public Count count;
        public Price price;
    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Meta {
//
//        public String estimatedLanguage;
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Name {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Number {

        public String text;
//        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class PaymentInfo {

        public Date date;
        public Time time;
        public CardInfo cardInfo;
        public ConfirmNum confirmNum;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Time{
        public String text;
//        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Price {

        public Price price;
        public UnitPrice unitPrice;
        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Receipt {

//        public Meta meta;
        public Result result;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Result {

        public StoreInfo storeInfo;
        public PaymentInfo paymentInfo;
        public ArrayList<SubResult> subResults;
        public TotalPrice totalPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class StoreInfo {

        public Name name;
        public BizNum bizNum;
        public SubName subName;
        public ArrayList<Address> addresses;
        public ArrayList<Tel> tel;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class SubName{
        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
//        public ArrayList<Object> maskingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class SubResult {
        public ArrayList<Item> items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class Tel {
        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TotalPrice {

        public Price price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown =true)
    public static class UnitPrice {

        public String text;
        public Formatted formatted;
//        public ArrayList<BoundingPoly> boundingPolys;
    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class ValidationResult {
//
//        public String result;
//    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
////    @JsonIgnoreProperties(ignoreUnknown =true)
//    public static class Vertex {
//
//        public double x;
//        public double y;
//    }


}