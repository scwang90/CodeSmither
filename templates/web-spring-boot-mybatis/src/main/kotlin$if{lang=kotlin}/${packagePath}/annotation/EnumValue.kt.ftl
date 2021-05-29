package ${packageName}.annotationimport ${packageName}.annotation.EnumValue.*import javax.validation.Constraintimport javax.validation.ConstraintValidatorimport javax.validation.ConstraintValidatorContextimport javax.validation.Payloadimport kotlin.reflect.KClass/** * 自定义枚举识别注解 * @author ${author} * @since ${now?string("yyyy-MM-dd zzzz")} */@MustBeDocumented@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)@Target(    AnnotationTarget.FUNCTION,    AnnotationTarget.PROPERTY_GETTER,    AnnotationTarget.PROPERTY_SETTER,    AnnotationTarget.FIELD,    AnnotationTarget.ANNOTATION_CLASS,    AnnotationTarget.CONSTRUCTOR,    AnnotationTarget.VALUE_PARAMETER)@Constraint(validatedBy = [EnumValueValidator::class, EnumValueValidatorInt::class, EnumValueValidatorShort::class])annotation class EnumValue( // 默认错误消息    val message: String = "无效的枚举值", val required: Boolean = false, val value: KClass<out Enum<*>> = Enum::class,    val groups: Array<KClass<*>> = [], val payload: Array<KClass<out  Payload>> = []) {    class EnumValueValidatorInt : ConstraintValidator<EnumValue, Int?> {        private var length = 0        private var required = false        //这个方法做一些初始化校验        override fun initialize(annotation: EnumValue) {            required = annotation.required            length = annotation.value.java.enumConstants.size        }        // 这个方法写具体的校验逻辑：校验数字是否属于指定枚举类型的范围        override fun isValid(value: Int?, context: ConstraintValidatorContext): Boolean {            return if (value == null) { !required } else value in 0 until length        }    }    class EnumValueValidatorShort : ConstraintValidator<EnumValue, Short> {        private var length = 0        private var required = false        //这个方法做一些初始化校验        override fun initialize(annotation: EnumValue) {            required = annotation.required            length = annotation.value.java.enumConstants.size        }        // 这个方法写具体的校验逻辑：校验数字是否属于指定枚举类型的范围        override fun isValid(value: Short?, context: ConstraintValidatorContext): Boolean {            return if (value == null) { !required } else value in 0 until length        }    }    class EnumValueValidator : ConstraintValidator<EnumValue, Enum<*>> {        private var required = false        //这个方法做一些初始化校验        override fun initialize(constraintAnnotation: EnumValue) {            required = constraintAnnotation.required        }        // 这个方法写具体的校验逻辑：校验数字是否属于指定枚举类型的范围        override fun isValid(value: Enum<*>?, context: ConstraintValidatorContext): Boolean {            return !required || value != null        }    }}