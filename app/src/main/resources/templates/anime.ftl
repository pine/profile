<#ftl strip_whitespace=true>
<#-- @ftlvariable name="layout" type="moe.pine.profile.models.ViewLayout" -->
<#-- @ftlvariable name="animeGroups" type="java.util.List<moe.pine.profile.models.ViewAnimeGroup>" -->

<#import "layout/base.ftl" as base>

<@base.page layout=layout>

  <section>
    <h3>アニメ全般</h3>
    <p>一般向けアニメも見ますが、深夜アニメが中心になります。</p>
  </section>

  <section>
    <h3>視聴済みアニメ</h3>
    <p>放送年別。OVA は発売年。</p>
    <p>最後まで視聴したもののみ。</p>

    <ul>
      <#list animeGroups as animeGroup>
        <li>
          ${animeGroup.name}
          <ul>
            <#list animeGroup.items as anime>
              <li>${anime.name}</li>
            </#list>
          </ul>
        </li>
      </#list>
    </ul>
  </section>
</@base.page>
