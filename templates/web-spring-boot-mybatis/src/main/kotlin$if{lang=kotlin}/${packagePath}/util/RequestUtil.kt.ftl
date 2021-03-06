package ${packageName}.util

import ${packageName}.exception.ClientException
import io.swagger.annotations.ApiModelProperty
import org.springframework.util.ObjectUtils

/**
 * Model 非空验证器
 * 主要用于更新接口 和 添加接口 使用同一个 Model 但是对非空检测有差异
 * 添加接口要求验证非空字段
 * 更新接口任何字段都可以为空
 * 要求：需要在 Model 中对非空字段添加 ApiModelProperty 注解，并设置 required=true
 * @author ${author}
 * @since ${now?string("yyyy-MM-dd zzzz")}
 */
object RequestUtil {

    /**
     * 验证客户端发送的参数中是否有不能为空，但是却没有传的参数
     * @param model 客户端发送参数
     */
    fun validate(model: Any?) {
        if (model == null) {
            return
        }
        try {
            val fields = model.javaClass.declaredFields
            for (field in fields) {
                if (field.isAnnotationPresent(ApiModelProperty::class.java)) {
                    val annotation = field.getAnnotation(ApiModelProperty::class.java)
                    if (annotation.required && "id" != field.name) {
                        field.isAccessible = true
                        val o = field[model]
                        if (o == null || o is String && ObjectUtils.isEmpty(o)) {
                            throw ClientException(annotation.value + "不能为空")
                        }
                    }
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    
}