${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
	<#include "KumsPojoTypeDeclaration.ftl"/> {

	<#if !pojo.isInterface()>	     

		<#include "KumsPojoFields.ftl"/>

		<#include "PojoConstructors.ftl"/>
   
		<#include "KumsPojoPropertyAccessors.ftl"/>

		<#include "PojoToString.ftl"/>

		<#include "PojoEqualsHashcode.ftl"/>

<#else>
	<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>

<#include "PojoExtraClassCode.ftl"/>

<#include "KumsPojoExtraCode.ftl"/>

}


</#assign>

${pojo.generateImports()}	

${classbody}











