package org.example.shiro;

/**
 * @author Derek-huang
 */

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 在前后端分离的场景下，session如何与前端建立联系
 * 只需要在getSessionId()方法中返回sessionId
 */
public class CustomizeSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //return super.getSessionId(request, response);针对前后端不分离的情况
        // String sessionId = WebUtils.getHttpRequest(request).getHeader("Authorization");
        // 如果从请求头中能获取到sessionId，需要将其余shiro内部管理的session进行关联，然后再返回
        ShiroHttpServletRequest req = (ShiroHttpServletRequest)request;
        String sessionId = req.getHeader("Authorization");
        if (null != sessionId){
            //这里的代码如何写？进入到super.getSessionId()中去，然后进入getReferenceSessionId()这个方法中
            //将所有的if(id != null) 这个判断下的代码全部拷贝过来
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            //automatically mark it valid here.  If it is invalid, the
            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return sessionId;
        }else {
            return super.getSessionId(request, response);

        }
    }
}

