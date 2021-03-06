package ${packageName}.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import ${packageName}.constant.ResultCode;
import ${packageName}.model.api.ApiResult;
import ${packageName}.model.api.LoginInfo;
<#if hasMultiLogin>
    <#list loginTables as table>
import ${packageName}.model.db.${table.className};
    </#list>
</#if>
import ${packageName}.service.AuthService;
import ${packageName}.shiro.model.JwtBearer;
import ${packageName}.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

/**
 * 登录认证 API 实现
 * @author ${author}
 * @since ${now?string("yyyy-MM-dd zzzz")}
 */
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Api(tags = "登录认证", description = "JWT Token 登录认证")
public class AuthController {

    private final AuthService service;

    @GetMapping("version")
    @ApiOperation(value = "版本信息", notes = "获取maven打包版本")
    public ApiResult<String> version() {
        String version = IndexController.class.getPackage().getImplementationVersion();
        if (version == null) {
            return ApiResult.success("调试版本");
        }
        return ApiResult.success(version);
    }
<#if hasMultiLogin>
    <#list loginTables as table>
    @PostMapping("login/${table.urlPathName}")
    @ApiOperation(value = "${table.remarkName}登录", notes = "针对${table.remark}的登录接口")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "form", name = "username", value = "登录账户", required = true, defaultValue = "admin"),
        @ApiImplicitParam(paramType = "form", name = "password", value = "登录密码", required = true, defaultValue = "admin")<#if table.hasOrgan>,</#if>
    <#if table.hasOrgan>
        @ApiImplicitParam(paramType = "form", name = "${table.orgColumn.fieldName}", value = "${table.orgColumn.remarkName}", required = true),
    </#if>
    })
    public ApiResult<LoginInfo<${table.className}>> login${table.className}(<#if table.hasOrgan>${table.orgColumn.fieldType} ${table.orgColumn.fieldName}, </#if>String username, String password, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        try {
            LoginInfo<${table.className}> info = service.login${table.className}(<#if table.hasOrgan>${table.orgColumn.fieldName}, </#if>username, password);
            JwtUtils.writeToHeader(info.token, request, response);
            return ApiResult.success(info);
        } catch (AuthenticationException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            log.error("登录失败", e);
            return ApiResult.failClient("登录失败");
        }
    }
    </#list>
<#else >
    @PostMapping("login")
    @ApiOperation(value = "登录", notes = "大部分接口都需要先登录后调用")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "form", name = "username", value = "登录账户", required = true, defaultValue = "admin"),
        @ApiImplicitParam(paramType = "form", name = "password", value = "登录密码", required = true, defaultValue = "admin")<#if table.hasOrgan>,</#if>
    <#if loginTable.hasOrgan>
        @ApiImplicitParam(paramType = "form", name = "${loginTable.orgColumn.fieldName}", value = "${table.orgColumn.remarkName}", required = true),
    </#if>
    })
    public ApiResult<LoginInfo<${table.className}>> login(<#if loginTable.hasOrgan>${loginTable.orgColumn.fieldType} ${loginTable.orgColumn.fieldName}, </#if>String username, String password, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        try {
            LoginInfo info = service.login(<#if loginTable.hasOrgan>${loginTable.orgColumn.fieldName}, </#if>username, password);
            JwtUtils.writeToHeader(info.token, request, response);
            return ApiResult.success(info);
        } catch (AuthenticationException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            log.error("登录失败", e);
            return ApiResult.failClient("登录失败");
        }
    }

</#if>
    @PostMapping("status")
    @ApiOperation(value = "登录状态")
    public ApiResult<Object> status() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return ApiResult.fail(ResultCode.Unauthorized);
        }
        JwtBearer bearer = subject.getPrincipals().oneByType(JwtBearer.class);
        DecodedJWT decoded = subject.getPrincipals().oneByType(DecodedJWT.class);
        return ApiResult.success(Arrays.asList(bearer,decoded));
    }

    @PostMapping("logout")
    @ApiOperation(value = "注销登录")
    public ApiResult<String> logout(HttpServletRequest request, HttpServletResponse response) {
        JwtUtils.writeToHeader("null", request, response);
        return ApiResult.success("注销登录成功");
    }

    @ApiIgnore
    @RequestMapping("failed")
    @ApiOperation(value = "请先登录", hidden = true)
    public ApiResult<Object> failed() {
        return ApiResult.fail(ResultCode.Unauthorized);
    }

    @ApiIgnore
    @RequestMapping("expired")
    @ApiOperation(value = "凭证过期", hidden = true)
    public ApiResult<Object> expired() {
        return ApiResult.fail(ResultCode.Unauthorized);
    }

}
