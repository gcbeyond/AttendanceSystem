package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.AdminPopedom;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/21 13:55
 * @Blog: ideashin.com
 */
public interface AdminPopedomDao {

    Boolean insert(AdminPopedom adminPopedom);

    Boolean delete(Integer popedomID);
}
