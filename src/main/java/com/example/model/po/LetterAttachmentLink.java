package com.example.model.po;

import com.example.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterAttachmentLink extends BaseModel {

    private static final long serialVersionUID = 1L;
    private Integer letterId;
    private Integer attachmentId;

    @Override
    public Map<String, String> pkMap() {
        Map<String, String> map = new HashMap<>();
        map.put("letterId", "letter_id");
        map.put("attachmentId", "attachment_id");
        return map;
    }

}
