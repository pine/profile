<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring>
<#import "functions.ftl" as functions>

<#macro page layout>
  <!DOCTYPE html>
  <html lang="ja">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<@spring.url '/stylesheets/html5reset.css' />">
    <link rel="stylesheet" href="<@spring.url '/stylesheets/main.css' />">
    <style>
      body {
        background-image: url("<@spring.url '/images/bg/' + layout.background />")
      }
    </style>
    <title>${layout.siteTitle?html}: ${layout.pageTitle?html}</title>
  </head>

  <body>
  <section>

    <header>
      <a href="/">
        <h1 lang="en">${layout.siteTitleEn?html}</h1>
        <p>${layout.siteTitle?html}</p>
      </a>
    </header>

    <div class="wrap">
      <nav>
        <header>
          <h2 lang="en">Menu</h2>
          <p>メニュー</p>
        </header>
        <@functions.menu pages=layout.pages />
      </nav>

      <article>
        <header>
          <h2 lang="en">${layout.pageTitleEn?html}</h2>
          <p>${layout.pageTitle?html}</p>
        </header>
        <#nested />
      </article>
    </div>

    <footer lang="en">
      <p>
        <small><a href="http://www.yunphoto.net" target="_blank" rel="noopener">Photo by (c)Tomo.Yun</a></small>
      </p>
      <p>
        <small>Powered by Spring Framework.</small>
      </p>
      <p>
        <small>This page is
          <a href="http://validator.w3.org/check?uri=referer" target="_blank" rel="noopener">valid HTML5</a>,
          and hosted on
          <a href="https://github.com/pine/profile-website" target="_blank" rel="noopener">GitHub</a>.
        </small>
      </p>
      <p>
        <small>Recommended Browsers: Firefox 3.5+, Google Chrome 7+, Opera 10.5+, Safari 5+</small>
      </p>
      <p>
        <small>Copyright &copy; 2011-2012, 2014-2019 <strong lang="ja">水音氷音 (ぴね)</strong>. All Rights Reserved.</small>
      </p>
    </footer>

  </section>
  </body>
  </html>
</#macro>
