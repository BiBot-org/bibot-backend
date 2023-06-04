package com.coderecipe.global.constant.dto;

import com.coderecipe.global.constant.enums.EventCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPayload {
    private String codeName;
    private String eventName;
    private Object body;

    public static KafkaPayload of(EventCode eventCode, Object data) {
        return new KafkaPayload(eventCode.getCodeName(), eventCode.getEventName(), data);
    }
}
