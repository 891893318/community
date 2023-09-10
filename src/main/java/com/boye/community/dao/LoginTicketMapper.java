package com.boye.community.dao;

import com.boye.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
@Deprecated
public interface LoginTicketMapper {


    /**
     * 实现Mapper:
     * 1：mapper中写配置文件
     * 2：Mapper接口中写注解（此次）,注解也可以写动态SQL，如update,好处方便，缺点不便于阅读，写错很烦不提示
     */
    //增加数据（登陆成功，插入凭证）
    @Insert({
            "insert into login_ticket (user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    //根据ticket查询，登录ticket表围绕ticket设计，
    //ticket市登录凭证，发送浏览器保存，其他数据服务端在数据库保存
    @Select({
            "select id, user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket} ",
    })
    LoginTicket selectByTicket(String ticket);

    //更改状态
    @Update({
            "<script>",
            "update  login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test = \"ticket!=null\">", //注意转义字符
            "and 1=1 ",
            "</if>",
            "</script>"     //别说写错了
    })
    int updateStatus(String ticket,int status);


}
