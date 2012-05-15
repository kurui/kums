    <property
        name="${property.name}"
    <#if property.value.typeName="Long"> type="long" <#else>  type="${property.value.typeName}"  </#if>
    
<#if !property.updateable>
        update="false"
</#if>
<#if !property.insertable>
        insert="false"
</#if>
<#if !property.basicPropertyAccessor>
        access="${property.propertyAccessorName}"
</#if>
<#if property.lazy>
        lazy="true"
</#if>
<#if !property.optimisticLocked>
        optimistic-lock="false"
</#if>
<#if property.value.hasFormula()>
<#assign formula = c2h.getFormulaForProperty(property)>
<#if formula?has_content>
        formula="${formula.text}"
</#if>
</#if>
    >
  <#assign metaattributable=property>
  <#include "meta.hbm.ftl">
  <#foreach column in property.columnIterator>
        <#include "column.hbm.ftl">
  </#foreach>	
  </property>

