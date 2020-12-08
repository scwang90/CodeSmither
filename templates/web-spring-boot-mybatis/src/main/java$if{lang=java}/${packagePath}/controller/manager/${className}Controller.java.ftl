package ${packageName}.controller.manager;

import com.github.pagehelper.PageRowBounds;
import ${packageName}.mapper.common.${className}Mapper;
import ${packageName}.model.api.ApiResult;
import ${packageName}.model.api.Paged;
import ${packageName}.model.api.Paging;
import ${packageName}.model.db.${className};
<#if !table.idColumn.autoIncrement && table.idColumn.stringType>
import ${packageName}.util.ID22;
</#if>
import ${packageName}.util.SqlIntent;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ${table.remark} 的 Controller 层实现
<#list table.descriptions as description>
 * ${description}
</#list>
 * @author ${author}
 * @since ${now?string("yyyy-MM-dd zzzz")}
 */
@RestController
@Api(tags = "${table.remark}")
@RequestMapping("/api/v1/${table.urlPathName}")
public class ${className}Controller {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ${className}Mapper mapper;

	public ${className}Controller(${className}Mapper mapper) {
		this.mapper = mapper;
	}

	@GetMapping
	@ApiOperation(value = "${table.remark}列表", notes = "分页参数支持两种形式，page 页数，skip 起始数据，两个传一个即可")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "size", value = "分页大小（配合 page 或 skip 组合使用）", required = true, defaultValue = "20"),
		@ApiImplicitParam(paramType = "query", name = "page", value = "分页页码（0开始，如果使用 skip 可不传）", defaultValue = "0"),
		@ApiImplicitParam(paramType = "query", name = "skip", value = "分页开始（0开始，如果使用 page 可不传）", defaultValue = "0")
	})
    public ApiResult<Paged<${className}>> list(@ApiIgnore Paging paging) {
		int count = mapper.countAll();
		List<${className}> list = mapper.findListIntent(SqlIntent.New(), new PageRowBounds(paging.start(), paging.count()));
		return ApiResult.success(new Paged<>(paging, list, count));
    }

    @PostMapping
    @ApiOperation(value = "添加${table.remark}", notes = <#if !table.idColumn.autoIncrement && table.idColumn.isStringType()>"返回新数据的Id"<#else>"返回是否成功"</#if>)
    @ApiImplicitParams({
		<#list table.columns as column>
		<#if column != table.idColumn>
		@ApiImplicitParam(paramType = "form", name = "${column.fieldName}", value = "${column.remark}<#if column.stringType && !column.name?matches("^\\w+?(ID|CODE)$")>（最多${column.length}字符）</#if>", dataType = "${column.fieldType?replace("short","int")?replace("java.util.Date","date")?lower_case}" <#if column.nullable!=true>, required = true</#if><#if column.defValue?length != 0>, defaultValue = "${column.defValue?trim}"</#if>)<#if column_has_next>,</#if>
		</#if>
		</#list>
    })
	<#if !table.idColumn.autoIncrement && table.idColumn.isStringType()>
	public ApiResult<String> insert(@Validated @ApiIgnore ${className} model) {
		if(model.get${table.idColumn.fieldNameUpper}() == null) {
			model.set${table.idColumn.fieldNameUpper}(ID22.random());
		}
	<#else>
	public ApiResult<Boolean> insert(@Validated @ApiIgnore ${className} model) {
	</#if>
	<#list table.columns as column>
		<#if column.fieldType == 'java.util.Date' && (column.name?lower_case == 'create_time' || column.name?lower_case == 'create_date')>
		model.set${column.fieldNameUpper}(new java.util.Date());
		</#if>
	</#list>
        mapper.insert(model);
	<#if !table.idColumn.autoIncrement && table.idColumn.isStringType()>
		return ApiResult.success(model.get${table.idColumn.fieldNameUpper}());
	<#else>
		return ApiResult.success(true);
	</#if>
	}

	@PutMapping
	@ApiOperation(value = "更新${table.remark}", notes = "返回数据修改的行数")
	@ApiImplicitParams({
	<#list table.columns as column>
		@ApiImplicitParam(paramType = "form", name = "${column.fieldName}", value = "${column.remark}<#if column.stringType && !column.name?matches("^\\w+?(ID|CODE)$")>（最多${column.length}字符）</#if>", dataType = "${column.fieldType?replace("short","int")?replace("java.util.Date","date")?lower_case}" <#if column.defValue?length != 0>, defaultValue = "${column.defValue?trim}"</#if>)<#if column_has_next>,</#if>
	</#list>
	})
    public ApiResult<Integer> update(@Validated @ApiIgnore ${className} model) {
		<#list table.columns as column>
			<#if column.fieldType == 'java.util.Date' && (column.name?lower_case == 'update_time' || column.name?lower_case == 'update_date')>
		model.set${column.fieldNameUpper}(new java.util.Date());
			</#if>
		</#list>
		return ApiResult.success(mapper.update(model));
	}

	@ApiOperation(value = "获取${table.remark}")
	@GetMapping("/{id}")
    public ApiResult<${className}> get(@PathVariable @ApiParam("${table.remark}Id") String id) {
		return ApiResult.success(mapper.findById(id));
	}

	@ApiOperation(value = "删除${table.remark}")
	@DeleteMapping("/{id}")
    public ApiResult<Integer> delete(@PathVariable @ApiParam("${table.remark}Id") String id) {
		return ApiResult.success(mapper.delete(id));
	}

}
