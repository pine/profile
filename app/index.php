<?php
require_once __DIR__.'/../vendor/autoload.php';

date_default_timezone_set('Asia/Tokyo');

$container = new \Slim\Container;

$app = new \Slim\App($container);

require_once __DIR__.'/routes.php';
require_once __DIR__.'/container/view.php';

$app->run();
