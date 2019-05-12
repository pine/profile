<#ftl strip_whitespace=true>

<#macro menu pages>
  <ul>
    <#list pages as page>
      <#if page.active>
        <li>${page.title?html}</li>
      <#else>
        <li><a href="${page.path?html}">${page.title?html}</a></li>
      </#if>
    </#list>
  </ul>
</#macro>
