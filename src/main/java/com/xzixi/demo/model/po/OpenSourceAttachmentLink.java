package com.xzixi.demo.model.po;

import com.xzixi.demo.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenSourceAttachmentLink extends BaseModel {

    private static final long serialVersionUID = 1L;
    private Integer openSourceId;
    private Integer attachmentId;

    @Override
    public Map<String, String> pkMap() {
        Map<String, String> map = new HashMap<>();
        map.put("openSourceId", "open_source_id");
        map.put("attachmentId", "attachment_id");
        return map;
    }

}
