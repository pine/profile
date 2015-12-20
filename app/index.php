<?php
require_once __DIR__.'/../vendor/autoload.php';

$container = new \Slim\Container;
$container['view'] = function ($c) {
    $view = new \Slim\Views\Twig(__DIR__.'/../views', [
        'debug'      => true,
        'autoreload' => true,
    ]);
    $view->addExtension(new \Slim\Views\TwigExtension(
        $c['router'],
        $c['request']->getUri()
    ));

    return $view;
};

$app = new \Slim\App($container);
require_once __DIR__.'/routes.php';
$app->run();
