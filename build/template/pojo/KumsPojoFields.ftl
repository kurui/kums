<#-- // Fields -->

private static final long serialVersionUID = 1L;

<#foreach field in pojo.getAllPropertiesIterator()>
	<#if pojo.getMetaAttribAsBool(field, "gen-property", true)> 
		<#if pojo.hasMetaAttribute(field, "field-description")>  
  			/**
    			 ${pojo.getFiefalseldJavaDoc(field, 0)}
     		*/
 		</#if>   		
 		protected <#if field.name="id">long<#else> ${pojo.getJavaTypeName(field, jdk5)}</#if> ${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5)}</#if>;
	</#if>
</#foreach>
