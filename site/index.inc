<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="utf-8">
<link rel="canonical" href="<?php echo $url ?>">
<?php if(IE && IE_VERSION_MAJOR <= 8): ?><script src="/html5.js"></script><?php endif ?>
<link rel="stylesheet" type="text/css" href="/html5reset.css">
<link rel="stylesheet" type="text/css" href="/index.css">
<?php if(IE && IE_VERSION_MAJOR >= 7 && IE_VERSION_MAJOR <= 8): ?><link rel="stylesheet" type="text/css" href="/index_lte_ie8.css"><?php endif ?>
<?php if(IE && IE_VERSION_MAJOR <= 7): ?><link rel="stylesheet" type="text/css" href="/index_lte_ie7.css"><?php endif ?>
<?php if(IE && IE_VERSION_MAJOR <= 6): ?><link rel="stylesheet" type="text/css" href="/index_lte_ie6.css"><?php endif ?>
<?php if(IE && IE_VERSION_MAJOR <= 7): ?><script src="/IE8.js"></script><?php endif ?>
<?php if(IE && IE_VERSION_MAJOR <= 6): ?><script src="/ie7-squish.js"></script><?php endif ?>
<style>

body {
	background-image: url("<?php echo $background ?>");
}

</style>
<?php if(IE && IE_VERSION_MAJOR <= 6): ?><script src="/index_lte_ie6.js"></script><?php endif ?>
<title>水音氷音 (ぴね) プロフィール: <?php echo $title_ja ?></title>
</head>

<body>

<?php include('browser_warning.inc') ?>

<!-- main section { -->
<section>

<!-- header { -->
<header>
<a href="/">
<h1 lang="en">Pine Profile</h1>
<p>水音氷音 (ぴね) プロフィール</p>
</a>
</header>
<!-- } header -->

<!-- wrap { -->
<div class="wrap">

<!-- menu { -->
<nav>

<!-- menu title { -->
<header>
<h2 lang="en">Menu</h2>
<p>メニュー</p>
</header>
<!-- } menu title -->

<!-- menu link { -->
<ul>
<?php foreach($menu as $key): ?>
<li><?php if($key != $page): ?><a href="/<?php echo $key ?>.html"><?php endif ?><?php echo $contents[$key]['title_ja'] ?><?php if($key != $page): ?></a><?php endif ?></li>
<?php endforeach ?>
</ul>
<!-- } menu link -->

</nav>
<!-- } menu -->

<!-- article { -->
<article>

<!-- article title { -->
<header>
<h2 lang="en"><?php echo $title_en ?></h2>
<p><?php echo $title_ja ?></p>
</header>
<!-- } article title -->

<!-- article text { -->
<?php include($document) ?>
<!-- } article text -->

<!-- article footer { -->
<footer>
<p>最終更新日時: <time datetime="<?php echo date(DATE_W3C, $last_modified) ?>"><?php echo date('Y年m月d日 H:i', $last_modified) ?></time></p>
</footer>
<!-- } article footer -->

</article>
<!-- } article -->

</div>
<!-- } wrap -->

<!-- footer { -->
<footer lang="en">
<p><small><a href="http://www.yunphoto.net">Photo by (c)Tomo.Yun</a></small></p>
<p><small>Powered by PHP <?php echo PHP_VERSION ?>, nginx.</small></p>
<p><small>This page is <a href="http://validator.w3.org/check?uri=referer">valid HTML5</a>, and hosted on <a href="https://github.com/pine613/profile-website">GitHub</a>.</small></p>
<p><small>Recommended Browsers: Firefox 3.5+, Google Chrome 7+, Opera 10.5+, Safari 5+</small></p>
<p><small>Copyright &copy; 2011-2012, 2014-2015 <strong lang="ja">水音氷音 (ぴね)</strong>. All Rights Reserved.</small></p>
</footer>
<!-- } footer -->

</section>
<!-- } main section -->


<?php if(PRESTO): ?>
<script>

// Presto バグ対策コード

(function(){
	var image = new Image();
	
	image.src = "<?php echo $background ?>";
	
	setTimeout(function(){
		
		if(image.complete){
			document.body.style.background = "url(" + image.src + ") fixed";
		}
		
		else {
			setTimeout(arguments.callee, 10);
		}
	}, 10);
})();

</script>
<?php endif ?>

</body>

</html>
