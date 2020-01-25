package com.legensity.b2bmall.module.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.legensity.b2bmall.module.user.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author jinbin
 * @date 2017-12-01 20:58
 */
public interface UserMapper  extends BaseMapper<User> {
    @Select("select * from t_user where mobile=#{mobile}")
    User selectUserByMobile(String mobile);

}
