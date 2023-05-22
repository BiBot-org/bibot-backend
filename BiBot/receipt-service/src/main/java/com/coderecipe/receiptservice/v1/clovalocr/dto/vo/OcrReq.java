package com.coderecipe.receiptservice.v1.clovalocr.dto.vo;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcrReq {

    private String version;
    private String requestId;
    private String timestamp;
    private Map<String,String> image;

}
