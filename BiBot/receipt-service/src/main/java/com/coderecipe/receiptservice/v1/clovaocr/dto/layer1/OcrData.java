package com.coderecipe.receiptservice.v1.clovaocr.dto.layer1;

import com.coderecipe.receiptservice.v1.clovaocr.dto.layer2.Images;
import com.coderecipe.receiptservice.v1.clovaocr.dto.layer2.Version;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OcrData {
    private String version;
    private Images images;
}
