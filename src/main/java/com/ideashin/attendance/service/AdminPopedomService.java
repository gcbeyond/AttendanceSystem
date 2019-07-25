package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.AdminPopedom;

/**
 * @Author: Shin
 * @Date: 2019/7/21 15:52
 * @Blog: ideashin.com
 */
public interface AdminPopedomService {
    Boolean addOnePopedom(AdminPopedom adminPopedom);

    Boolean removeOnePopedom(Integer popedomID);
}
