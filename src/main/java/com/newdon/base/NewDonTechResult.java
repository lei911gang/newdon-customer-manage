package com.newdon.base;

import com.newdon.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @ClassName Result
 * @Auther: Dong
 * @Date: 2019/1/5 1:39
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDonTechResult implements Serializable {
    private Integer status;
    private String message;
    private Object data;
    private long total;
    private boolean success;
    private String techMsg;

    public static NewDonTechResult build(Integer status, String message, Object data, String techMsg) {
        boolean falg = true;
        if (!CommonConstants.SUCCESS_CODE.equals(status)) {
            falg = false;
        }
        return new NewDonTechResult(status, message, data, 0, falg, techMsg);
    }
}
