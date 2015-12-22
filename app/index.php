<?php
require_once __DIR__.'/../vendor/autoload.php';

$container = new \Slim\Container;
require_once __DIR__.'/container/view.php';

$app = new \Slim\App($container);
require_once __DIR__.'/routes.php';
$app->run();
