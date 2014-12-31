<?php
	// 設定ファイル
	
	$default         = 'default';
	$not_found       = '404';
	$contents_dir    = dirname(__FILE__).'/contents'; // 絶対パス
	$backgrounds_dir = 'img/bg'; // 相対URL
	$url_base        = 'http://profile.pine.moe';
	
	// メニュー
	$menu = array(
		'default'
	);
	
	// ページ一覧
	$contents = array(
		'default' => array(
			'document' => $contents_dir.'/default.inc',
			'title_ja' => 'トップページ',
			'title_en' => 'Top Page'
		),
        
        'site_info' => array(
			'document' => $contents_dir.'/site_info.inc',
			'title_ja' => 'サイト情報',
			'title_en' => 'Site Information'
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