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
public class UserRoleLink extends BaseModel {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer roleId;

    @Override
    public Map<String, String> pkMap() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "user_id");
        map.put("roleId", "role_id");
        return map;
    }

}
