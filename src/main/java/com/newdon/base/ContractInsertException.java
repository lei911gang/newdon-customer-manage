package com.newdon.base;

/**
 * @author Rlserim
 * @create  2018/8/15 17:10
 * @desc
 **/
public class ContractInsertException extends BaseException {
    public ContractInsertException(int status, String msg) {
        super(status, msg);
    }
}
