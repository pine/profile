<?php
require_once __DIR__.'/../views/menu.php';

$container['view'] = function ($c) {
    $view = new \Slim\Views\Twig(__DIR__.'/../../views', [
        'debug'      => true,
        'autoreload' => true,
    ]);
    $view->addExtension(new \Slim\Views\TwigExtension(
        $c['router'],
        $c['request']->getUri()
    ));
    $view->addExtension(new \Views\MenuExtension(
        $c['router'],
        $c['request']
    ));
	$view['php_version'] = PHP_VERSION;

    return $view;
};
