<#ftl strip_whitespace=true>

<#macro menu pages>
  <ul>
    <#list pages as page>
      <#if page.active>
        <li>${page.title}</li>
      <#else>
        <li><a href="${page.path}">${page.title}</a></li>
      </#if>
    </#list>
  </ul>
</#macro>
