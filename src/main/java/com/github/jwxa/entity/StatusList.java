package com.github.jwxa.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: Jwxa Date: 2018/2/4 ProjectName: weibotool Version: 1.0
 */
@Data
public class StatusList {
    public ArrayList<Status> statuses = new ArrayList<Status>();
    public boolean hasvisible;
    public String previous_cursor;
    public String next_cursor;
    public int total_number;
    public long since_id;
    public long max_id;
    public long has_unread;
}
