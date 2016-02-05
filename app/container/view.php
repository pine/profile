<?php
require_once __DIR__.'/../views/menu.php';
require_once __DIR__.'/../views/background.php';
require_once __DIR__.'/../views/last_modified.php';

$container['view'] = function ($c) use($routes) {
    $view = new \Slim\Views\Twig(__DIR__.'/../../views', [
        'debug'      => true,
        'autoreload' => true,
    ]);

    $view['php_version'] = PHP_VERSION;

    $view->addExtension(new \Slim\Views\TwigExtension(
        $c['router'],
        $c['request']->getUri()
    ));

    $view->addExtension(new \Views\MenuExtension(
        $c['router'],
        $c['request'],
        $routes
    ));

    $view->addExtension(new \Views\BackgroundExtension);
    //$view->addExtension(new \Views\LastModifiedExtension);

    return $view;
};

$container['notFoundHandler'] = function ($c) {
    return function ($request, $response) use ($c) {
        return $c->view->render($response, '404.html');
    };
};
