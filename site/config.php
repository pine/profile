<?php
	// 設定ファイル
	
	$default         = 'default';
	$not_found       = '404';
	$contents_dir    = dirname(__FILE__).'/contents'; // 絶対パス
	$backgrounds_dir = '/img/bg'; // URL
	$url_base        = 'http://profile.pine.moe';
	
	// メニュー
	$menu = array(
		'default',
		'program',
		'anime',
        'light_novel',
        'link',
        'site_info'
	);
	
	// ページ一覧
	$contents = array(
		'default' => array(
			'document' => $contents_dir.'/default.inc',
			'title_ja' => 'トップページ',
			'title_en' => 'Top Page'
		),
		
		'program' => array(
			'document' => $contents_dir.'/program.inc',
			'title_ja' => 'プログラミング',
			'title_en' => 'Programing'
		),
		
		'anime' => array(
			'document' => $contents_dir.'/anime.inc',
			'title_ja' => 'アニメ',
			'title_en' => 'Anime'
		),
		
        'light_novel' => array(
			'document' => $contents_dir.'/light_novel.inc',
			'title_ja' => 'ライトノベル',
			'title_en' => 'Light Novel'
		),
		
        'site_info' => array(
			'document' => $contents_dir.'/site_info.inc',
			'title_ja' => 'サイト情報',
			'title_en' => 'Site Information'
		),
		
		'link' => array(
			'document' => $contents_dir.'/link.inc',
			'title_ja' => 'リンク',
			'title_en' => 'Link'
		),
		
		'404' => array(
			'document' => $contents_dir.'/404.inc',
			'title_ja' => 'ページが見つかりません',
			'title_en' => '404 Not Found'
		)
	);
	
	// 背景画像
	$backgrounds = array(
		$backgrounds_dir.'/yun_1375.jpg',
		$backgrounds_dir.'/yun_7604.jpg',
		$backgrounds_dir.'/yun_3218.jpg',
		$backgrounds_dir.'/yun_1077.jpg',
		$backgrounds_dir.'/yun_3281.jpg'
	);
?>