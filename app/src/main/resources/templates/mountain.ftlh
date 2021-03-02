<#ftl strip_whitespace=true>
<#-- @ftlvariable name="layout" type="moe.pine.profile.models.ViewLayout" -->
<#-- @ftlvariable name="mountains" type="java.util.List<moe.pine.profile.models.ViewMountain>" -->

<#import "layout/base.ftlh" as base>

<@base.page layout=layout>
  <section>
    <h3>登山</h3>
    <p>2017 年の夏より登山をはじめました。</p>
  </section>

  <section>
    <h3>登った山一覧</h3>
    <p>頂上まで登った山の一覧です。</p>
    <ul>
        <#list mountains as mountain>
          <li>${mountain.climbedAt} ${mountain.name} ... ${mountain.height}</li>
        </#list>
    </ul>
  </section>
</@base.page>
