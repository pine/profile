<?php
	require_once(dirname(__FILE__).'/config.php');
	
	ini_set('display_errors', 'On');
	error_reporting(-1);
	
	// 文字コードの設定
	mb_internal_encoding('utf-8');
	mb_http_output('utf-8');
	
	// タイムゾーンの設定
	date_default_timezone_set('Asia/Tokyo');
	
	// Windows で実行されているか返す
	function is_windows(){
		return strtoupper(substr(PHP_OS, 0, 3)) == 'WIN';
	}
	
	// Internet Explorer のメジャーバージョンを取得する
	function get_ie_major_version($user_agent = null){
		
		if($user_agent == null){
			$user_agent = $_SERVER['HTTP_USER_AGENT'];
		}
		
		if(preg_match('/MSIE ([0-9]{1,}[\.0-9]{0,})/', $user_agent, $matches)){
			return intval($matches[1]);
		}
		
		return null;
	}
	
    // Trident のバージョンを返す
    function get_trident_version(){
        $user_agent = $_SERVER['HTTP_USER_AGENT'];
		
		if(preg_match('/Trident\/([\d\.]+)/', $user_agent, $matches)){
			return $matches[1];
		}
        
        return null;
    }
    
	// WebP をサポートしているか返す
	function is_support_webp($user_agent = null){
		
		if($user_agent == null){
			$user_agent = $_SERVER['HTTP_USER_AGENT'];
		}
		
		if(preg_match('/Presto\/([\d\.]+)/', $user_agent, $matches)){
			return version_compare('2.8', $matches[1], '<=');
		//	return (float)$matches[1] >= 2.8;
		}
		
		if(preg_match('/Chrome\/([\d\.]+)/', $user_agent, $matches)){
			return version_compare('9.0', $matches[1], '<=');
		//	return (float)$matches[1] >= 9.0;
		}
		
		return false;
	}
	
    // JPEG XR がサポートされる環境であるか返す
    function is_support_jxr(){
        return get_ie_major_version() >= 9 || version_compare('7.0', get_trident_version(), '<=');
    }
    
	// IsPresto
	function is_presto(){
		$user_agent = $_SERVER['HTTP_USER_AGENT'];
		
		return !!preg_match('/Presto/', $user_agent);
	}

	function get_last_modified($page){
		if(is_windows()){
			return NULL;
		}
		
    	$date = `bash last_modified.sh $page`;
    
		if (preg_match('/^null/', $date)) {
			return NULL;
		}
		
		return strtotime($date);
	}
	
	// page パラメータが渡されている場合
	if(!@empty($_GET['page'])){
		// page パラメーターが有効な場合
		if(array_key_exists($_GET['page'], $contents)){
			$page = $_GET['page'];
		}
		
		// page パラメーターが無効な場合
		// 404 エラーの処理
		else {
			header('HTTP/1.1 404 Not Found');
			$page = $not_found;
		}
	}
	
	// page パラメーターが渡されていないか、ページが存在しない場合
	else {
		$page = $default;
	}
	
	// テンプレートに渡す変数
	$document   = $contents[$page]['document'];
	$title_ja   = $contents[$page]['title_ja'];
	$title_en   = $contents[$page]['title_en'];
	$background = $backgrounds[array_rand($backgrounds)];
	$url        = $url_base.'/'.$page.'.html';
    
    $last_modified = get_last_modified($page);
	
	if($page == $default){
		$url = $url_base;
	}
	
	if(get_ie_major_version()){
		define('IE', true);
		define('IE_VERSION_MAJOR', get_ie_major_version());
	}
	
	else {
		define('IE', false);
	}
	
	define('PRESTO', is_presto());
	
	// IE9 以降なら JPEG XR に拡張子を変更する。
	if(is_support_jxr()){
		$background = str_replace('.jpg', '.wdp', $background);
	}
	
	// WebP に対応しているなら拡張子を変更する。
	if(is_support_webp()){
		$background = str_replace('.jpg', '.webp', $background);
	}
	
	// テンプレートを読み込む
	include(dirname(__FILE__).'/index.inc');
?>
